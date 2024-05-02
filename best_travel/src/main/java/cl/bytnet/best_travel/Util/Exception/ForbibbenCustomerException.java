package cl.bytnet.best_travel.Util.Exception;

public class ForbibbenCustomerException extends RuntimeException {
    public ForbibbenCustomerException(){
        super("This customer is blocked");
    }
}
