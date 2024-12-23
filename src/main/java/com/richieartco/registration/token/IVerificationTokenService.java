package com.richieartco.registration.token;

import com.richieartco.user.User;

import java.util.Optional;

/**
 * @author Alfred Ochola
 */

public interface IVerificationTokenService {
    String validateToken(String token);
    void saveVerificationTokenForUser(User user, String token);
    Optional<VerificationToken> findByToken(String token);


    void deleteUserToken(Long id);
}
