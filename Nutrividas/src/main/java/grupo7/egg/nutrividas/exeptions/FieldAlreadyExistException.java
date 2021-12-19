package grupo7.egg.nutrividas.exeptions;

public class FieldAlreadyExistException extends ConflictException{
    private static final String DESCRIPTION ="";

    public FieldAlreadyExistException(String message) {
        super(DESCRIPTION+""+message);
    }
}
