package demo.simple.library.model.dto.user;

import lombok.Data;

@Data
public class UserLoginDTORequest {

    private String username;
    private String password;
}
