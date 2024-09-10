package giorgiaipsarop.u5d7BlogAndAuthor.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("Elemento con id " + id + " non è stato trovato");
    }
}