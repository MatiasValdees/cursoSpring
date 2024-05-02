package cl.bytnet.best_travel.Util.Exception;



public class UsernameNotFoundException extends RuntimeException{
    private static final String ERROR_MESSAGE="Username %s no exist";

    public UsernameNotFoundException(String username){
        super(String.format(ERROR_MESSAGE,username));
    }
}
