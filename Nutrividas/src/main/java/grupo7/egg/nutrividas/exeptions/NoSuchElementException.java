package grupo7.egg.nutrividas.exeptions;

public class NoSuchElementException extends NotFoundException{
    private static final String DESCRIPTION ="";

    public NoSuchElementException(String message){super((DESCRIPTION+". "+message));}
}
