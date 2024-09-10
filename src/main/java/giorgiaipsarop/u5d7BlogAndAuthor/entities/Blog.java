package giorgiaipsarop.u5d7BlogAndAuthor.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Blog {
    private int id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private int timeOfLecture;

    public Blog(int id, String category, String title, String cover, String content, int timeOfLecture) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.timeOfLecture = timeOfLecture;
    }
}