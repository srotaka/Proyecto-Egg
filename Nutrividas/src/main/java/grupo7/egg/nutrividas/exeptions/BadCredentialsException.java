package grupo7.egg.nutrividas.exeptions;

public class BadCredentialsException extends ForbiddenException {
    private static final String DESCRIPTION ="";

    public BadCredentialsException(String message) {
        super(DESCRIPTION+""+message);
    }
}
