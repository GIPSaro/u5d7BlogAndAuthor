package giorgiaipsarop.u5d7BlogAndAuthor.controllers;

import giorgiaipsarop.u5d7BlogAndAuthor.entities.Blog;
import giorgiaipsarop.u5d7BlogAndAuthor.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {


    @Autowired
    private BlogService blogService;
    @GetMapping
    public List<Blog> getAllUsers() {
        return this.blogService.getUsers();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Status Code 201
    public Blog save(@RequestBody Blog blog) {
        return this.blogService.save(blog);
    }
    @GetMapping("/{id}")
    public Blog findById(@PathVariable int id) {
        return this.blogService.findById(id);
    }
    @PutMapping("/{id}")
    public Blog findByIdAndUpdate(@PathVariable int id, @RequestBody Blog updatedBlog) {
        return this.blogService.findByIdAndUpdate(id, updatedBlog);
    }

    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT) // Status Code 204
    public String findByIdAndDelete(@PathVariable int id) {
        return this.blogService.findByIdAndDelete(id) ? "Elemento rimosso" : "Elemento non trovato";
    }
}
