package demo.simple.library.mapper;

import demo.simple.library.model.dto.user.UserDTORequest;
import demo.simple.library.model.dto.user.UserDTOResponse;
import demo.simple.library.model.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTOResponse toUserDTOResponse(User user);

    User toEntity(UserDTORequest userDTORequest);

    void updateEntityFromDTO(UserDTORequest userDTORequest, @MappingTarget User user);
}
