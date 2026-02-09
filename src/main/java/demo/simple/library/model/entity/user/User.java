package demo.simple.library.model.entity.user;

import demo.simple.library.model.entity.request.Request;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
//@DiscriminatorColumn(name="role", discriminatorType=DiscriminatorType.STRING)
//@DiscriminatorValue("DEFAULT")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Request> requests;

}
