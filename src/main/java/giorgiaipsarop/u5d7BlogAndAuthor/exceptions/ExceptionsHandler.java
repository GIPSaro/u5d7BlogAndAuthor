package giorgiaipsarop.u5d7BlogAndAuthor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsPayload handleBadRequest(BadRequestException ex) {
        if (ex.getErrorsList() != null) {
            // Se c'è la lista degli errori il payload di errore dovrà contenere la lista errori
            List<String> errorsList = ex.getErrorsList().stream().map(objectError -> objectError.getDefaultMessage()).toList();
            return new ErrorsPayloadWithList(ex.getMessage(), LocalDateTime.now(), errorsList);
        } else {
            //Altrimenti il payload di errore dovrà avere un solo errore
            return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
        }
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsPayload handleNotFound(NotFoundException ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class) // Tutti gli altri tipi di eccezioni non elencati sopra
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorsPayload handleGenericErrors(Exception ex) {
        ex.printStackTrace(); // N.B. non dimentichiamoci che è ESTREMAMENTE UTILE tenere traccia di chi ha causato l'errore per poterlo fixare
        return new ErrorsPayload("Problema lato server!!", LocalDateTime.now());
    }
}