package com.richieartco.registration;

import com.richieartco.user.Role;
import lombok.Data;

import java.util.List;

/**
 * @author Alfred Ochola
 */
@Data
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private List<Role> roles;
}
