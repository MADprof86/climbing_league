package pl.coderslab.climbingleague.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NonNull
    @Column(nullable = false, unique = true)
    private String email;
    @Size(min = 8)
    @Column(nullable = false)
    private String password;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String profilePicture;

    public enum Role {
        USER, ADMIN, SUPERADMIN
    }
}
