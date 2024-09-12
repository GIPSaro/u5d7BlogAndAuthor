package giorgiaipsarop.u5d7BlogAndAuthor.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giorgiaipsarop.u5d7BlogAndAuthor.entities.Blog;
import giorgiaipsarop.u5d7BlogAndAuthor.exceptions.BadRequestException;
import giorgiaipsarop.u5d7BlogAndAuthor.exceptions.NotFoundException;

import giorgiaipsarop.u5d7BlogAndAuthor.payloads.NewBlogDTO;
import giorgiaipsarop.u5d7BlogAndAuthor.repositories.BlogsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@Slf4j
public class BlogService {
    @Autowired
    private BlogsRepository blogsRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private Cloudinary cloudinaryUploader;

    public Page<Blog> getBlogs(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return blogsRepository.findAll(pageable);
    }

    public Blog save(NewBlogDTO newBlogPayload) {
        //LOGICA PAYLOAD
        blogsRepository.findByTitle(newBlogPayload.title()).ifPresent(blog -> {

            throw new BadRequestException("Il blogpost " + newBlogPayload.title() + " esiste già!");

        });
        Blog blog = new Blog(
                newBlogPayload.category(),
                newBlogPayload.title(),
                newBlogPayload.cover(),
                newBlogPayload.content(),
                newBlogPayload.timeOfLecture()
        );
        blog.setAuthor(authorService.findById(newBlogPayload.authorId()));
        return blogsRepository.save(blog);
    }

    public Blog findById(int blogId) {
        return blogsRepository.findById(blogId).orElseThrow(() -> new NotFoundException(blogId));
    }


    public Blog findByIdAndUpdate(int blogId, NewBlogDTO updatedBlog) {
        Blog found = this.findById(blogId);
        found.setCategory(updatedBlog.category());
        found.setTitle(updatedBlog.title());
        found.setCover(updatedBlog.cover());
        found.setContent(updatedBlog.content());
        found.setTimeOfLecture(updatedBlog.timeOfLecture());
        found.setAuthor(authorService.findById(updatedBlog.authorId()));
        return blogsRepository.save(found);
    }

    public void findByIdAndDelete(int blogId) {
        Blog found = this.findById(blogId);
        blogsRepository.delete(found);
    }
    public String uploadImageAndGetUrl(MultipartFile cover, int blogId) throws IOException {
        String urlCover = (String) cloudinaryUploader.uploader().upload(cover.getBytes(), ObjectUtils.emptyMap()).get("url");
        Blog found = findById(blogId);
        found.setCover(urlCover);
        blogsRepository.save(found);
        return urlCover;
    }
    }

