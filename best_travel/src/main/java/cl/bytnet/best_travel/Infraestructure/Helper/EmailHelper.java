package cl.bytnet.best_travel.Infraestructure.Helper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class EmailHelper {
    private final JavaMailSender emailSender;

    public void sendMail(String to,String nameClient, String productName){
        MimeMessage message=emailSender.createMimeMessage();
        String html=readHTML(nameClient,productName);
        try {
            message.setFrom(new InternetAddress("Contacto@biofarma.cl"));
            message.setSubject("Compra");
            message.setRecipients(MimeMessage.RecipientType.TO,to);
            message.setContent(html, MediaType.TEXT_HTML_VALUE);
            emailSender.send(message);
        }catch (MessagingException e){
            log.error("Error to send email "+e);

        }
    }
    private String readHTML(String nameClient,String productName){

        try(var lines= Files.lines(template)){
            var html=lines.collect(Collectors.joining()); //metodo join concatena todo como string
            return html.replace("{name}",nameClient).replace("{product}",productName);
        }catch (IOException e){
            log.error("Cant read HTML template, "+e);
            throw  new RuntimeException();
        }
    }
    private final Path template= Paths.get("src/main/resources/email/template.html");
}
