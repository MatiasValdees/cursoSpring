package cl.bytnet.best_travel.API.Model.Response.Error;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ResponseErrorR extends BaseError{
    private String message;
}
