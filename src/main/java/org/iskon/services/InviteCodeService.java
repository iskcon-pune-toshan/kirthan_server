package org.iskon.services;

public interface InviteCodeService {
    void inviteUserByCode(String email, String invitedBy) throws Exception;
    boolean isInviteCodeRedeemable(String email, String code);
    void redeemInviteCode(String email, String code)  throws Exception;
}
