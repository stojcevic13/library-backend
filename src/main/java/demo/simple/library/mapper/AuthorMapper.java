package demo.simple.library.mapper;

import demo.simple.library.model.dto.author.AuthorDTORequest;
import demo.simple.library.model.dto.author.AuthorDTOResponse;
import demo.simple.library.model.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    // Entity -> AuthorDTOResponse
    AuthorDTOResponse toDTOResponse(Author author);

    // AuthorDTORequest -> Entity
    Author toEntity(AuthorDTORequest authorDTORequest);

    void updateEntityFromDto(AuthorDTORequest authorDTORequest, @MappingTarget Author author);
}
