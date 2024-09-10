package giorgiaipsarop.u5d7BlogAndAuthor.services;

import giorgiaipsarop.u5d7BlogAndAuthor.entities.Blog;
import giorgiaipsarop.u5d7BlogAndAuthor.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class BlogService {
    private List<Blog> blogs = new ArrayList<>();

    public List<Blog> getUsers() {
        return this.blogs;
    }

    public Blog save(Blog blog) {
        Random random = new Random();
        blog.setId(random.nextInt(1, 10000));
        this.blogs.add(blog);
        return blog;
    }

    public Blog findById(int id) {
        Blog found = null;
        for (Blog blog : this.blogs) {
            if (blog.getId() == id) {
                found = blog;
            }
        }
        if (found == null) throw new NotFoundException(id);
        else return found;
    }

    public Blog findByIdAndUpdate(int id, Blog updatedBlog) {
        Blog found = null;
        for (Blog blog : this.blogs) {
            if (blog.getId() == id) {
                found = blog;
                found.setCategory(updatedBlog.getCategory());
                found.setTitle(updatedBlog.getTitle());
                found.setCover(updatedBlog.getCover());
                found.setContent(updatedBlog.getContent());
                found.setTimeOfLecture(updatedBlog.getTimeOfLecture());
            }
        }
        if (found == null) throw new NotFoundException(id);
        else return found;
    }

    public boolean findByIdAndDelete(int id) {
        return this.blogs.removeIf(current -> current.getId() == id);
        }
    }

