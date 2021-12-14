package grupo7.egg.nutrividas.exeptions;

public class BadRequestException extends RuntimeException{
    private static final String DESCRIPTION ="";

    public BadRequestException(String message){super((DESCRIPTION+". "+message));}
}
