package org.iskon.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Value("${user.jwt.user.username}")
    private String username;
    @Value("${user.jwt.user.password}")
    private String password;

    private Map<String, UserDetails> users = new HashMap<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void postConstruct() {
        UserDetails user = User
                .withUsername(username)
                .password(passwordEncoder.encode(password))
                .roles("ADMIN", "USER")
                .build();

        users.put(username, user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = users.get(username);
        if(userDetails == null){
            throw new RuntimeException("User not found: " + username);
        }
        return  userDetails;
    }
}
