package giorgiaipsarop.u5d7BlogAndAuthor.services;

import giorgiaipsarop.u5d7BlogAndAuthor.entities.Blog;
import giorgiaipsarop.u5d7BlogAndAuthor.exceptions.BadRequestException;
import giorgiaipsarop.u5d7BlogAndAuthor.exceptions.NotFoundException;
import giorgiaipsarop.u5d7BlogAndAuthor.payloads.BlogPayload;
import giorgiaipsarop.u5d7BlogAndAuthor.repositories.BlogsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;




@Service
@Slf4j
public class BlogService {
    @Autowired
    private BlogsRepository blogsRepository;
    @Autowired
    private AuthorService authorService;

    public Page<Blog> getBlogs(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return blogsRepository.findAll(pageable);
    }

    public Blog save(BlogPayload blogPayload) {
        //LOGICA PAYLOAD
        blogsRepository.findByTitle(blogPayload.getTitle()).ifPresent(blog -> {

            throw new BadRequestException("Il blogpost " + blogPayload.getTitle() + " esiste già!");

        });
        Blog blog = new Blog(
                blogPayload.getCategory(),
                blogPayload.getTitle(),
                blogPayload.getCover(),
                blogPayload.getContent(),
                blogPayload.getTimeOfLecture()
        );
        blog.setAuthor(authorService.findById(blogPayload.getAuthorId()));
        return blogsRepository.save(blog);
    }

    public Blog findById(int blogId) {
        return blogsRepository.findById(blogId).orElseThrow(() -> new NotFoundException(blogId));
    }


    public Blog findByIdAndUpdate(int blogId, Blog updatedBlog) {
        Blog found = this.findById(blogId);
        found.setCategory(updatedBlog.getCategory());
        found.setTitle(updatedBlog.getTitle());
        found.setCover(updatedBlog.getCover());
        found.setContent(updatedBlog.getContent());
        found.setTimeOfLecture(updatedBlog.getTimeOfLecture());
        return blogsRepository.save(found);
    }

    public void findByIdAndDelete(int blogId) {
        Blog found = this.findById(blogId);
        blogsRepository.delete(found);
    }
    }

