package demo.simple.library.mapper;

import demo.simple.library.model.dto.user.UserDTO;
import demo.simple.library.model.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toUserDTO(User user);


}
