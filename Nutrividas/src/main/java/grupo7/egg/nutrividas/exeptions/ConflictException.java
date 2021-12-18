package grupo7.egg.nutrividas.exeptions;

public class ConflictException extends RuntimeException{
    private static final String DESCRIPTION ="";

    public ConflictException(String message) {
        super(DESCRIPTION+""+message);
    }
}
