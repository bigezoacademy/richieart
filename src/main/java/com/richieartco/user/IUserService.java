package com.richieartco.user;

import com.richieartco.registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;

/**
 * @author Alfred Ochola
 */

public interface IUserService {
    List<User> getAllUsers();
    User registerUser(RegistrationRequest registrationRequest);
   Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    void updateUser(Long id, String firstName,String lastName,String phone,  String email);

    void deleteUser(Long id);
}
