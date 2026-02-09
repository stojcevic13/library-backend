package demo.simple.library.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginDTOResponse {

    private UserDTO user;
    private String token;

}
