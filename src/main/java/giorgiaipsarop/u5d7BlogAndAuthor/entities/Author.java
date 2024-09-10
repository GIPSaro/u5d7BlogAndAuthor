package giorgiaipsarop.u5d7BlogAndAuthor.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Author {
    private int id;
    private String name;
    private String surname;
    private String email;
    private LocalDate dateOfBirth;
    private String avatar;

    public Author(int id, String name, String surname, String email, LocalDate dateOfBirth, String avatar) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.avatar = "https://ui-avatars.com/api/?name=" + name + "+" + surname;
    }
}
