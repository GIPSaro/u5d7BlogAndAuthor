package giorgiaipsarop.u5d7BlogAndAuthor.exceptions;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException {
    private List<ObjectError> errorsList;
    public BadRequestException(@NotEmpty @Size String errorsList) {
        super("Errori nel payload");
        this.errorsList = errorsList;
    }
}