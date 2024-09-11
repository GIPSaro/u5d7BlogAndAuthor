package giorgiaipsarop.u5d7BlogAndAuthor.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogPayload {
    private String category;
    private String title;
    private String cover;
    private String content;
    private int timeOfLecture;
    private int authorId;

    public BlogPayload(String category, String title, String cover, String content, int timeOfLecture, int authorId) {
        this.category = category;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.timeOfLecture = timeOfLecture;
        this.authorId = authorId;
    }
}