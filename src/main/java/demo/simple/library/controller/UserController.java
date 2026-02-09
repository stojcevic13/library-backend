package demo.simple.library.controller;

import demo.simple.library.model.dto.user.UserDTO;
import demo.simple.library.model.dto.user.UserLoginDTO;
import demo.simple.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserLoginDTO userLoginDTO){
        UserDTO userDTO = this.userService.loginUser(userLoginDTO);
        return ResponseEntity.ok(userDTO);
    }

}
