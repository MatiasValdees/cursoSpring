package cl.bytnet.best_travel.Aspects;

import cl.bytnet.best_travel.Util.Annotation.Notify;
import cl.bytnet.best_travel.Util.BestTravelUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect
public class NotifyAspect {

    //punto de insercion @Before()  @After() @Around()
    @After(value = "@annotation(cl.bytnet.best_travel.Util.Annotation.Notify)")
    public void notifyInFile (JoinPoint joinPoint) throws IOException {//aspecto siempre vacio
        var args=joinPoint.getArgs();
        var size=args[1];
        var order=args[2]==null?"NONE":args[2];


        //PATH
        var signature=(MethodSignature)joinPoint.getSignature();
        var method=signature.getMethod();
        var annotation=method.getAnnotation(Notify.class); //leer el path qu esta en la clase notify
        //BestTravelUtil.writeNotification(text,annotation.value());


        var text= String.format(LINE_FORMAT, LocalDateTime.now(),annotation.value(),size,order);

        BestTravelUtil.writeNotification(text,PATH);
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
    }

    private static final String PATH="files/notify.txt";
    private static final String LINE_FORMAT="At %s new request %s, with size page %s and order %s"; //%s string/ %f number
}
