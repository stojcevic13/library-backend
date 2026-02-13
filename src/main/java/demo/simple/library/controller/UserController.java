package demo.simple.library.controller;

import demo.simple.library.model.dto.user.UserDTORequest;
import demo.simple.library.model.dto.user.UserDTOResponse;
import demo.simple.library.model.dto.user.UserLoginDTORequest;
import demo.simple.library.model.dto.user.UserLoginDTOResponse;
import demo.simple.library.model.entity.user.User;
import demo.simple.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @PutMapping()
//    public ResponseEntity<Void> updateUsers() {
//        userService.updatePasswords();
//        return ResponseEntity.noContent().build();  // 204 - NO CONTENT
//    }

    @PreAuthorize("hasAnyRole('LIBRARIAN')")
    @GetMapping
    public ResponseEntity<List<UserDTOResponse>> findAll() {
        List<UserDTOResponse> users = userService.findAll();
        return ResponseEntity.ok(users);    // 200 - OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTOResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id));     // 200 - OK
    }

    @PreAuthorize("hasAnyRole('LIBRARIAN', 'MANAGER')")
    @PostMapping()
    public ResponseEntity<UserDTOResponse> createUser(@RequestBody UserDTORequest userDTORequest) {
        UserDTOResponse userDTOResponse = userService.createUser(userDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTOResponse);     // 201 - CREATED
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTOResponse> updateUser(@PathVariable Integer id, @RequestBody UserDTORequest userDTORequest) {
        UserDTOResponse userDTOResponse = userService.updateUser(id, userDTORequest);
        return ResponseEntity.ok(userDTOResponse);  // 200 - OK
    }

    @PreAuthorize("hasAnyRole('LIBRARIAN', 'MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();  // 204 - NO CONTENT
    }

}
