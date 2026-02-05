package demo.simple.library.service;

import demo.simple.library.mapper.AuthorMapper;
import demo.simple.library.model.dto.author.AuthorDTORequest;
import demo.simple.library.model.dto.author.AuthorDTOResponse;
import demo.simple.library.model.entity.Author;
import demo.simple.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    public List<AuthorDTOResponse> findAll(){
        return authorRepository.findAll().stream().map(authorMapper::toDTOResponse).toList();
    }

    public AuthorDTOResponse findById(Integer id) {
        return authorRepository.findById(id).map(authorMapper::toDTOResponse).orElse(null);
    }

    public AuthorDTOResponse createAuthor(AuthorDTORequest authorDTORequest) {
        Author author = authorMapper.toEntity(authorDTORequest);
        authorRepository.save(author);
        return authorMapper.toDTOResponse(author);
    }

    public AuthorDTOResponse updateAuthor(Integer id, AuthorDTORequest authorDTORequest) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found!"));
        authorMapper.updateEntityFromDto(authorDTORequest, author);
        authorRepository.save(author);
        return authorMapper.toDTOResponse(author);
    }

    public void deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
    }
}
