package demo.simple.library.controller;

import demo.simple.library.model.dto.author.AuthorDTORequest;
import demo.simple.library.model.dto.author.AuthorDTOResponse;
import demo.simple.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<AuthorDTOResponse> findAll(){
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public AuthorDTOResponse findById(@PathVariable Integer id){
        return authorService.findById(id);
    }

    @PostMapping
    public AuthorDTOResponse createAuthor(@RequestBody AuthorDTORequest authorDTORequest) {
        return authorService.createAuthor(authorDTORequest);
    }

    @PutMapping("/{id}")
    public AuthorDTOResponse updateAuthor(@PathVariable Integer id, @RequestBody AuthorDTORequest authorDTORequest) {
        return authorService.updateAuthor(id, authorDTORequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthor(id);
    }
}
