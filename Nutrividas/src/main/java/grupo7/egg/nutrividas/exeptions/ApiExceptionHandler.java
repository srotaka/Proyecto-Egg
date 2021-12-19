package grupo7.egg.nutrividas.exeptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, NoSuchElementException.class})
    @ResponseBody
    public ErrorMessage notFoundRequest(HttpServletRequest request, NoHandlerFoundException exception){
        return new ErrorMessage(exception,request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            DuplicateKeyException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            org.springframework.web.bind.MethodArgumentNotValidException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request,Exception exception){
        return new ErrorMessage(exception,request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ForbiddenException.class/*,
    org.springframework.security.authentication.InternalAuthenticationServiceException.class*/})
    @ResponseBody
    public ErrorMessage forbiddenRequest(HttpServletRequest request,Exception exception){
        return new ErrorMessage(exception,request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ConflictException.class})
    @ResponseBody
    public ErrorMessage conflictRequest(HttpServletRequest request,Exception exception){
        return new ErrorMessage(exception,request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class, AccessDeniedException.class})
    public ErrorMessage unauthorized(HttpServletRequest request,Exception exception){
        return new ErrorMessage(exception,request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ErrorMessage fatalErrorUnexpectedErrorRequest(HttpServletRequest request,Exception exception){
        return new ErrorMessage(exception,request.getRequestURI());
    }
}
