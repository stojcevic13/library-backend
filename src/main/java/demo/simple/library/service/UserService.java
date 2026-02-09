package demo.simple.library.service;

import demo.simple.library.mapper.UserMapper;
import demo.simple.library.model.JwtUtil;
import demo.simple.library.model.dto.user.UserDTO;
import demo.simple.library.model.dto.user.UserLoginDTORequest;
import demo.simple.library.model.dto.user.UserLoginDTOResponse;
import demo.simple.library.model.entity.user.User;
import demo.simple.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;


    public UserLoginDTOResponse loginUser(UserLoginDTORequest userLoginDTO) {
        User user = userRepository.findByUsername(userLoginDTO.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        if (!user.getPassword().equals(userLoginDTO.getPassword())) {
            throw new RuntimeException("Incorrect password!");
        }

        String token = JwtUtil.generateToken(user.getUsername(), user.getRole().name());
        return new UserLoginDTOResponse(userMapper.toUserDTO(user), token);
    }
}
