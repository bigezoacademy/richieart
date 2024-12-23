package com.richieartco.registration.password;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Alfred Ochola
 */

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String theToken);
}
