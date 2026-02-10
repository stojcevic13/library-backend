package demo.simple.library.model.dto.user;

import demo.simple.library.model.entity.user.AccountStatus;
import demo.simple.library.model.entity.user.Role;
import lombok.Data;

@Data
public class UserDTORequest {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Role role;
    private AccountStatus status;
}
