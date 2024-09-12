package giorgiaipsarop.u5d7BlogAndAuthor.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record NewBlogDTO(
        @NotEmpty(message = "La categoria è obbligatoria")
        @Size(min = 2, max = 20, message = "La categoria deve essere compresa tra 2 e 20 caratteri")
        String category,
        @NotEmpty(message = "Il titolo è obbligatorio")
        @Size(min = 2, message = "Il titolo deve essere di almeno 5 caratteri")
        String title,
        @NotEmpty(message = "La cover è obbligatoria")
        @URL
        String cover,
        @NotEmpty(message = "Il contenuto è obbligatorio")
        @Size(min = 10, message = "Il contenuto deve essere di almeno 10 caratteri")
        String content,
        @NotNull(message = "Il tempo di lettura è obbligatorio")
        @Min(value = 1, message = "Il tempo di lettura deve essere di almeno 1 minuto")
        int timeOfLecture,
        @NotNull(message = "L'id dell'autore è obbligatorio")
        int authorId
) {
}
