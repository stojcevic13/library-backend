package demo.simple.library.service;

import demo.simple.library.mapper.UserMapper;
import demo.simple.library.model.dto.user.UserDTO;
import demo.simple.library.model.dto.user.UserLoginDTO;
import demo.simple.library.model.entity.user.User;
import demo.simple.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;


    public UserDTO loginUser(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByUsername(userLoginDTO.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        if (!user.getPassword().equals(userLoginDTO.getPassword())) {
            throw new RuntimeException("Incorrect password!");
        }
        return userMapper.toUserDTO(user);
    }
}
