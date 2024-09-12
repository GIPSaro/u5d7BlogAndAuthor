package giorgiaipsarop.u5d7BlogAndAuthor.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giorgiaipsarop.u5d7BlogAndAuthor.entities.Author;
import giorgiaipsarop.u5d7BlogAndAuthor.exceptions.BadRequestException;
import giorgiaipsarop.u5d7BlogAndAuthor.exceptions.NotFoundException;
import giorgiaipsarop.u5d7BlogAndAuthor.payloads.NewAuthorDTO;
import giorgiaipsarop.u5d7BlogAndAuthor.repositories.AuthorsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class AuthorService {
    private List<Author> authors = new ArrayList<>();
    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Page<Author> getAuthors(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return authorsRepository.findAll(pageable);
    }

    public Author save(NewAuthorDTO newAuthorPayload) {
        authorsRepository.findByEmail(newAuthorPayload.email()).ifPresent(author -> {
            throw new BadRequestException("L'email " + newAuthorPayload.email() + " è già in uso!");
        });
        Author author = new Author(
                newAuthorPayload.name(),
                newAuthorPayload.surname(),
                newAuthorPayload.email(),
                newAuthorPayload.dateOfBirth()
        );
        author.setAvatar(createAvatarUrl(newAuthorPayload));
        return authorsRepository.save(author);
    }

    public Author findById(int authorId) {
        return authorsRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public Author findByIdAndUpdate(int authorId, NewAuthorDTO updatedAuthor) {
        Author found = this.findById(authorId);
        found.setName(updatedAuthor.name());
        found.setSurname(updatedAuthor.surname());
        found.setEmail(updatedAuthor.email());
        found.setDateOfBirth(updatedAuthor.dateOfBirth());
        return authorsRepository.save(found);
    }

    public void findByIdAndDelete(int authorId) {
        Author found = this.findById(authorId);
        authorsRepository.delete(found);
    }

    public String createAvatarUrl(NewAuthorDTO author) {
        return "https://ui-avatars.com/api/?name=" + author.name() + "+" + author.surname();
    }

    public String uploadImageAndGetUrl(MultipartFile cover, int authorId) throws IOException {
        String urlCover = (String) cloudinaryUploader.uploader().upload(cover.getBytes(), ObjectUtils.emptyMap()).get("url");
        Author found = findById(authorId);
        found.setAvatar(urlCover);
        authorsRepository.save(found);
        return urlCover;
    }
}