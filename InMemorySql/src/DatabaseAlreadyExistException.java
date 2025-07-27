public class DatabaseAlreadyExistException extends RuntimeException{
    public DatabaseAlreadyExistException(String message) {
        super(message);
    }
}
