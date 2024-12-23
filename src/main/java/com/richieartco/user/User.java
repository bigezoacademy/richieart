package com.richieartco.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.Collection;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "unique_email", columnNames = {"email"}),
        @UniqueConstraint(name = "unique_phone", columnNames = {"phone"})
})
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @NaturalId(mutable = true)
    private String phone; // Changed field name to "phone"
    @NaturalId(mutable = true)
    private String email;



    private String password;
    private boolean isEnabled = false;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public User(String firstName, String lastName,String phone, String email,
                 String password, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
