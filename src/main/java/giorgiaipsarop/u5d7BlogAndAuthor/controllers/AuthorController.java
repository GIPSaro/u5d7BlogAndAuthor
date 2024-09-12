package giorgiaipsarop.u5d7BlogAndAuthor.controllers;

import giorgiaipsarop.u5d7BlogAndAuthor.entities.Author;
import giorgiaipsarop.u5d7BlogAndAuthor.exceptions.BadRequestException;
import giorgiaipsarop.u5d7BlogAndAuthor.payloads.NewAuthorDTO;
import giorgiaipsarop.u5d7BlogAndAuthor.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Page<Author> getAllAuthors(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String orderBy) {
        return this.authorService.getAuthors(page, size, orderBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Status Code 201
    public Author save(@RequestBody @Validated NewAuthorDTO newAuthorDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.authorService.save(newAuthorDTO);
    }

    //Aggiorniamo l'immagine con questo endpoint
    @PatchMapping("/{id}/uploadAvatar")
    @ResponseStatus(HttpStatus.OK) // Status Code 200
    public String uploadCover(@PathVariable int id, @RequestParam("avatar") MultipartFile image) throws IOException {
        return this.authorService.uploadImageAndGetUrl(image, id);
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable int id) {
        return this.authorService.findById(id);
    }

    @PutMapping("/{id}")
    public Author findByIdAndUpdate(@PathVariable int id, @RequestBody @Validated NewAuthorDTO updatedAuthor, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.authorService.findByIdAndUpdate(id, updatedAuthor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Status Code 204
    public void findByIdAndDelete(@PathVariable int id) {
        this.authorService.findByIdAndDelete(id);
    }
}
