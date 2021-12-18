package grupo7.egg.nutrividas.exeptions;

public class ForbiddenException extends RuntimeException{
    private static final String DESCRIPTION ="";

    public ForbiddenException(String message) {
        super(DESCRIPTION+""+message);
    }
}
