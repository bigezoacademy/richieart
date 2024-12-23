package com.richieartco.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * @author Alfred Ochola
 */

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token);

    void deleteByUserId(Long id);
}
