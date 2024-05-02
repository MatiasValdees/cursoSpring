package cl.bytnet.best_travel.API.Controllers.Error_Handler;


import cl.bytnet.best_travel.API.Model.Response.Error.BaseError;
import cl.bytnet.best_travel.API.Model.Response.Error.ErrorsResponse;
import cl.bytnet.best_travel.API.Model.Response.Error.ResponseErrorR;
import cl.bytnet.best_travel.Util.Exception.IdNotFoundException;
import cl.bytnet.best_travel.Util.Exception.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Set;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErrorController extends RuntimeException{
    @ExceptionHandler({IdNotFoundException.class, UsernameNotFoundException.class})
    public BaseError NotFound(RuntimeException e){
        return ResponseErrorR.builder()
                .message(e.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseError Bad (MethodArgumentNotValidException e){
        var errors=new ArrayList<String>();
        e.getAllErrors().forEach(error-> errors.add(error.getDefaultMessage()));
        return ErrorsResponse.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
    }
}
