package com.richieartco.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author Alfred Ochola
 */

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE User u set u.firstName =:firstName, "+
            " u.lastName =:lastName,u.phone=:phone," + "u.email =:email where u.id =:id")
    void update(String firstName,String lastName, String phone, String email, Long id);
}
