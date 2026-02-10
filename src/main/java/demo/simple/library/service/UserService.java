package demo.simple.library.service;

import demo.simple.library.mapper.UserMapper;
import demo.simple.library.model.JwtUtil;
import demo.simple.library.model.dto.user.UserDTORequest;
import demo.simple.library.model.dto.user.UserDTOResponse;
import demo.simple.library.model.dto.user.UserLoginDTORequest;
import demo.simple.library.model.dto.user.UserLoginDTOResponse;
import demo.simple.library.model.entity.user.User;
import demo.simple.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        userRepository.save(user);
        return userMapper.toUserDTOResponse(user);
    }


    public UserDTOResponse updateUser(Integer id, UserDTORequest userDTORequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        userMapper.updateEntityFromDTO(userDTORequest, user);
        userRepository.save(user);
        return userMapper.toUserDTOResponse(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
