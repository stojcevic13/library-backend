package demo.simple.library.service;

import demo.simple.library.mapper.UserMapper;
import demo.simple.library.security.JwtUtil;
import demo.simple.library.model.dto.user.UserDTORequest;
import demo.simple.library.model.dto.user.UserDTOResponse;
import demo.simple.library.model.dto.user.UserLoginDTORequest;
import demo.simple.library.model.dto.user.UserLoginDTOResponse;
import demo.simple.library.model.entity.user.User;
import demo.simple.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired JwtUtil jwtUtil;

    private final UserMapper userMapper = UserMapper.INSTANCE;


    public UserLoginDTOResponse loginUser(UserLoginDTORequest userLoginDTO) {
        User user = userRepository.findByUsername(userLoginDTO.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found!");
        }

        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect password!");
        }

        String token = jwtUtil.generateToken(
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername()).
                        password(user.getPassword()).
                        roles(user.getRole().name())
                        .build());
        return new UserLoginDTOResponse(userMapper.toUserDTOResponse(user), token);
    }

    public List<UserDTOResponse> findAll() {
        return userRepository.findAll().stream().map(userMapper::toUserDTOResponse).toList();
    }

    public UserDTOResponse findById(Integer id) {
        return userRepository.findById(id).map(userMapper::toUserDTOResponse).orElse(null);
    }

    public UserDTOResponse createUser(UserDTORequest userDTORequest) {
        User user = userMapper.toEntity(userDTORequest);
        user.setPassword(passwordEncoder.encode(userDTORequest.getPassword()));
        userRepository.save(user);
        return userMapper.toUserDTOResponse(user);
    }


    public UserDTOResponse updateUser(Integer id, UserDTORequest userDTORequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        userMapper.updateEntityFromDTO(userDTORequest, user);
        user.setPassword(passwordEncoder.encode(userDTORequest.getPassword()));
        userRepository.save(user);
        return userMapper.toUserDTOResponse(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public void updatePasswords() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        }
    }
}
