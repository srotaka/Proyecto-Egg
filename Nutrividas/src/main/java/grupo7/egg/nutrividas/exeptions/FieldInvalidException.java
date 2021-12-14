package grupo7.egg.nutrividas.exeptions;

public class FieldInvalidException extends BadRequestException{
    private static final String DESCRIPTION ="";

    public FieldInvalidException(String message){super((DESCRIPTION+". "+message));}
}
