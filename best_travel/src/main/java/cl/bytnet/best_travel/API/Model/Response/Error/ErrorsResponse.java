package cl.bytnet.best_travel.API.Model.Response.Error;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
public class ErrorsResponse extends BaseError{
    private List<String> errors;

}
