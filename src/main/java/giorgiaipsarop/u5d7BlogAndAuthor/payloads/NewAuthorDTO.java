package giorgiaipsarop.u5d7BlogAndAuthor.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewAuthorDTO(
        @NotEmpty(message = "Il nome è obbligatorio")
        @Size(min = 2, message = "Il titolo deve essere di almeno 2 caratteri")
        String name,
        @NotEmpty(message = "Il cognome è obbligatorio")
        @Size(min = 2, message = "Il cognome deve essere di almeno 2 caratteri")
        String surname,
        @NotEmpty(message = "l'email è obbligatoria")
        @Email
        String email,
        @NotNull(message = "La data di nascita è obbligatoria")
        LocalDate dateOfBirth
) {
}