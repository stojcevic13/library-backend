package demo.simple.library.controller;

import demo.simple.library.model.dto.user.UserDTO;
import demo.simple.library.model.dto.user.UserLoginDTORequest;
import demo.simple.library.model.dto.user.UserLoginDTOResponse;
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

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public ResponseEntity<UserLoginDTOResponse> loginUser(@RequestBody UserLoginDTORequest userLoginDTO){
        UserLoginDTOResponse userLoginDTOResponse = userService.loginUser(userLoginDTO);
        return ResponseEntity.ok(userLoginDTOResponse);
    }

}
