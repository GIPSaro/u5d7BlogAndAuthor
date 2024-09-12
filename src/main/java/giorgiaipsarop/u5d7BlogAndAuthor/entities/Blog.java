package giorgiaipsarop.u5d7BlogAndAuthor.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "blogs")
@NoArgsConstructor
public class Blog {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private int timeOfLecture;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Blog(String category, String title, String content, int timeOfLecture) {
        this.category = category;
        this.title = title;
        this.cover = "https://picsum.photos/200/300";
        this.content = content;
        this.timeOfLecture = timeOfLecture;
    }
}