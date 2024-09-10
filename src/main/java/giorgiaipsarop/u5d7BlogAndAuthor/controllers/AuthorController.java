package giorgiaipsarop.u5d7BlogAndAuthor.controllers;

import giorgiaipsarop.u5d7BlogAndAuthor.entities.Author;
import giorgiaipsarop.u5d7BlogAndAuthor.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")

public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllUsers() {
        return this.authorService.getUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Status Code 201
    public Author save(@RequestBody Author author) {
        return this.authorService.save(author);
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable int id) {
        return this.authorService.findById(id);
    }

    @PutMapping("/{id}")
    public Author findByIdAndUpdate(@PathVariable int id, @RequestBody Author updatedAuthor) {
        return this.authorService.findByIdAndUpdate(id, updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public String findByIdAndDelete(@PathVariable int id) {
        return this.authorService.findByIdAndDelete(id) ? "Elemento rimosso" : "Elemento non trovato";
    }

}
