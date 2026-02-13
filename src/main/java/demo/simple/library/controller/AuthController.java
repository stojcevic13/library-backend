package demo.simple.library.controller;

import demo.simple.library.model.dto.user.UserLoginDTORequest;
import demo.simple.library.model.dto.user.UserLoginDTOResponse;
import demo.simple.library.security.JwtUtil;
import demo.simple.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<UserLoginDTOResponse> loginUser(@RequestBody UserLoginDTORequest userLoginDTO){
        UserLoginDTOResponse userLoginDTOResponse = userService.loginUser(userLoginDTO);
        return ResponseEntity.ok(userLoginDTOResponse);
    }
}
