package grupo7.egg.nutrividas.exeptions;

import org.springframework.validation.BindingResult;

public class InvalidDataException extends BadRequestException{
    private static final String DESCRIPTION ="";

    private final transient BindingResult result;

    public InvalidDataException(BindingResult result) {
        super("");
        this.result = result;
    }
    public InvalidDataException(String message, BindingResult result){super((DESCRIPTION+". "+message));
        this.result = result;
    }
}
