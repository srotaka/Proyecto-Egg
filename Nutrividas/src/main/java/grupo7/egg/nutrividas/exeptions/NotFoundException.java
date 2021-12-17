package grupo7.egg.nutrividas.exeptions;

public class NotFoundException extends RuntimeException{
    private static final String DESCRIPTION ="";

    public NotFoundException(String message) {
        super(DESCRIPTION+""+message);
    }
}
