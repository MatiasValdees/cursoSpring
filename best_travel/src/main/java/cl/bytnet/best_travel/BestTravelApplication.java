package cl.bytnet.best_travel;

import cl.bytnet.best_travel.Domain.Repositories.Document.AppUserRepository;
import cl.bytnet.best_travel.Domain.Repositories.JPA.FlyRepository;
import cl.bytnet.best_travel.Domain.Repositories.JPA.TicketRepository;
import cl.bytnet.best_travel.Domain.Repositories.JPA.TourRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLOutput;

@AllArgsConstructor
@Data
@SpringBootApplication
public class BestTravelApplication implements CommandLineRunner{

	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BestTravelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		var fly=flyRepository.findById(1L).orElseThrow();
//		System.out.println(fly);
//		var ticket=ticketRepository.findById(UUID.fromString("12345678-1234-5678-2236-567812345678")).orElseThrow();
//		System.out.println(ticket);

//		var tour=tourRepository.findById(1L).orElseThrow();
//		System.out.println(tour);
//var user=this.appUserRepository.findByUsername("misterX").get();
//		System.out.println(user);
	//appUserRepository.findAll().forEach(user-> System.out.println(user.getUsername() +"---"+this.bCryptPasswordEncoder.encode(user.getPassword())));



	}

}