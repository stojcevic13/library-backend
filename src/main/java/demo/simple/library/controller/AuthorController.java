package demo.simple.library.controller;

import demo.simple.library.model.dto.author.AuthorDTORequest;
import demo.simple.library.model.dto.author.AuthorDTOResponse;
import demo.simple.library.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTOResponse>> findAll() {
        List<AuthorDTOResponse> authors = authorService.findAll();
        return ResponseEntity.ok(authors);      // 200 - OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTOResponse> findById(@PathVariable Integer id) {
        AuthorDTOResponse author = authorService.findById(id);
        return (author != null) ? ResponseEntity.ok(author) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AuthorDTOResponse> createAuthor(@RequestBody AuthorDTORequest authorDTORequest) {
        AuthorDTOResponse author = authorService.createAuthor(authorDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);  // 201 - CREATED
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTOResponse> updateAuthor(@PathVariable Integer id, @RequestBody AuthorDTORequest authorDTORequest) {
        AuthorDTOResponse updatedAuthor = authorService.updateAuthor(id, authorDTORequest);
        return ResponseEntity.ok(updatedAuthor);    // 200 - OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();  // 204 - NO CONTENT
    }
}
