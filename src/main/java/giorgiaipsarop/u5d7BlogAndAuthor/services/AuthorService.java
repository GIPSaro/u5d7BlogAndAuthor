package giorgiaipsarop.u5d7BlogAndAuthor.services;

import giorgiaipsarop.u5d7BlogAndAuthor.entities.Author;
import giorgiaipsarop.u5d7BlogAndAuthor.exceptions.BadRequestException;
import giorgiaipsarop.u5d7BlogAndAuthor.exceptions.NotFoundException;
import giorgiaipsarop.u5d7BlogAndAuthor.repositories.AuthorsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class AuthorService {
    private List<Author> authors = new ArrayList<>();
    @Autowired
    private AuthorsRepository authorsRepository;



    public Page<Author> getAuthors(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return authorsRepository.findAll(pageable);
    }


    public String getFormattedAuthorDateOfBirth(Author author) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return author.getDateOfBirth().format(formatter);
    }
    public Author save(Author newAuthor) {
        authorsRepository.findByEmail(newAuthor.getEmail()).ifPresent(author -> {
            throw new BadRequestException("L'email " + author.getEmail() + " è già in uso!");
        });
        newAuthor.setAvatar(createAvatarUrl(newAuthor));
        return authorsRepository.save(newAuthor);
    }
    public String createAvatarUrl(Author author) {
        return "https://ui-avatars.com/api/?name=" + author.getName() + "+" + author.getSurname();

    }
    public Author findById(int authorId) {
            return authorsRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

public Author findByIdAndUpdate(int authorId, Author updatedAuthor) {
    Author found = this.findById(authorId);
    found.setName(updatedAuthor.getName());
    found.setSurname(updatedAuthor.getSurname());
    found.setEmail(updatedAuthor.getEmail());
    found.setDateOfBirth(updatedAuthor.getDateOfBirth());
    found.setAvatar(createAvatarUrl(found));
    return authorsRepository.save(found);
}


public void findByIdAndDelete(int authorId) {
    Author found = this.findById(authorId);
    authorsRepository.delete(found);
}
}

