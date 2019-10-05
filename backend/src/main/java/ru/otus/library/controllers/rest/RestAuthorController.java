package ru.otus.library.controllers.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.model.Author;
import ru.otus.library.services.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class RestAuthorController {

    private final AuthorService authorService;

    @GetMapping("/authors")
    public List<Author> getAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/authors/{id}")
    public Author getAuthor(@PathVariable("id") String id) {
        return authorService.findById(id).orElseThrow(() -> new RuntimeException("no Author with id " + id));
    }

    @PostMapping("/authors")
    public Author create(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @PutMapping("/authors/{id}")
    public Author update(@RequestBody Author author,
                       @PathVariable String id) {
        authorService.findById(id).orElseThrow(() -> new RuntimeException("no author with id " + id));
        author.setId(id);
        return authorService.saveAuthor(author);
    }

    @DeleteMapping("/authors/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable String id) {
        Author author = authorService.findById(id).orElseThrow(() -> new RuntimeException("no Author with id " + id));
        String deletedId = authorService.delete(author);
        log.info("Deleted author with id {}",deletedId);
    }

}
