package demo.simple.library.mapper;

import demo.simple.library.model.dto.request.RequestDTOResponse;
import demo.simple.library.model.entity.request.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestMapper {

    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(source = "book.title", target = "book")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    RequestDTOResponse toDTOResponse(Request request);


}
