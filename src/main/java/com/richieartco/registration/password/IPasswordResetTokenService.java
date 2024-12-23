package com.richieartco.registration.password;

import com.richieartco.user.User;

import java.util.Optional;

/**
 * @author Alfred Ochola
 */

public interface IPasswordResetTokenService {

    String validatePasswordResetToken(String theToken);

    Optional<User> findUserByPasswordResetToken(String theToken);

    void resetPassword(User theUser, String password);

    void createPasswordResetTokenForUser(User user, String passwordResetToken);
}
