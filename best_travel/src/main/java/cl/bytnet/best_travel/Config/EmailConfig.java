package cl.bytnet.best_travel.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
//@Configuration
public class EmailConfig {
//    @Bean
//    public JavaMailSender getJavaMailSender(){
//        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//
//        mailSender.setUsername("best_travel");
//        mailSender.setPassword("cbaz mhmp pprm zmkl");
//        Properties props=mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol","smtp");
//        props.put("mail.smtp.auth","true");
//        props.put("mail.smtp.starttls.enable","true");
//        props.put("mail.debug","true");
//        return mailSender;
//    }

}
