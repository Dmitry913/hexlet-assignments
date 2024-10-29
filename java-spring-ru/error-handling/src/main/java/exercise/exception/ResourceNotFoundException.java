package exercise.exception;

// BEGIN
public class ResourceNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Product with id %s not found";
    public ResourceNotFoundException(String id) {
        super(String.format(MESSAGE, id));
    }
}
// END
