package org.iskon.services;

import org.iskon.constants.InviteCodeStatus;
import org.iskon.models.InviteCode;
import org.iskon.repositories.InviteCodeJpaRepository;
import org.iskon.utils.OTPGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service("InviteCodeService")
public class InviteCodeServiceImpl implements InviteCodeService {

    @Autowired
    private InviteCodeJpaRepository inviteCodeJpaRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void inviteUserByCode(String email, String invitedBy) throws Exception {
        Optional<InviteCode> optionalInviteCode = inviteCodeJpaRepository.findById(email);
        InviteCode inviteCode = null;
        if(optionalInviteCode.isPresent()) {
           inviteCode = optionalInviteCode.get();
        }
        sendInviteCode(email, inviteCode, invitedBy);
    }

    private void sendInviteCode(String email, InviteCode inviteCode, String invitedBy) throws Exception {
        if(null == inviteCode){
            String generatedCode = OTPGenerator.random(6);
            inviteCode = InviteCode.builder()
                    .email(email)
                    .code(generatedCode)
                    .createdBy(invitedBy)
                    .createdTime(new Date())
                    .status(InviteCodeStatus.GENERATED.name())
                    .build();
            inviteCodeJpaRepository.save(inviteCode);
        } else {
            String inviteCodeStatus = inviteCode.getStatus();
            if (inviteCodeStatus.equals(InviteCodeStatus.REDEEMED.name())) {
                String generatedCode = OTPGenerator.random(6);
                inviteCode.setCode(generatedCode);
                inviteCode.setStatus(InviteCodeStatus.GENERATED.name());
                inviteCode.setCreatedBy(invitedBy);
                inviteCode.setCreatedTime(new Date());
                inviteCodeJpaRepository.save(inviteCode);
            }
        }
        if(null == inviteCode.getCode() || inviteCode.getCode().isEmpty())
            throw new Exception("Database corruption: Database entry is present but Code is missing!");

        emailService.sendSimpleMessage(email,"Your invitation to form team", "Hare Krishna! Please use your invite code "
                + inviteCode.getCode() + " to create your team on Volstory app");
        inviteCode.setCreatedTime(new Date());
        inviteCode.setCreatedBy(invitedBy);
        inviteCode.setStatus(InviteCodeStatus.INVITED.name());
        inviteCodeJpaRepository.save(inviteCode);
    }

    @Override
    public boolean isInviteCodeRedeemable(String email, String code) {
        Optional<InviteCode> optionalInviteCode = inviteCodeJpaRepository.findById(email);
        InviteCode inviteCode = null;
        if(optionalInviteCode.isPresent()) {
            inviteCode = optionalInviteCode.get();
            String codeFromDB = inviteCode.getCode();
            String inviteCodeStatus = inviteCode.getStatus();
            return codeFromDB.equals(code) && inviteCodeStatus.equals(InviteCodeStatus.INVITED.name());
        }
        return false;
    }

    @Override
    public void redeemInviteCode(String email, String code) throws Exception {
        Optional<InviteCode> optionalInviteCode = inviteCodeJpaRepository.findById(email);
        InviteCode inviteCode = null;
        if(optionalInviteCode.isPresent()) {
            inviteCode = optionalInviteCode.get();
            String codeFromDB = inviteCode.getCode();
            String inviteCodeStatus = inviteCode.getStatus();
            if (null == inviteCodeStatus || inviteCodeStatus.isEmpty()) {
                throw new Exception("Redeem failed : [Database corruption] Database entry is present but Status is missing!");
            }
            if(codeFromDB.equals(inviteCode.getCode())){
                //redeemable
                if(!inviteCodeStatus.equals(InviteCodeStatus.REDEEMED.name())){
                    //redeem
                    inviteCode.setStatus(InviteCodeStatus.REDEEMED.name());
                    inviteCodeJpaRepository.save(inviteCode);
                }
                //already redeemed
            }
        } else {
            throw new Exception("Redeem failed : [Invalid code] The given code " + code + " cannot be redeemed for the user " + email);
        }
    }
}
