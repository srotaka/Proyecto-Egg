package grupo7.egg.nutrividas.exeptions;

public class UnauthorizedException extends RuntimeException{
    private static final String DESCRIPTION ="";

    public UnauthorizedException(String message) {
        super((DESCRIPTION+""+message));
    }
}
