package teamfeedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository repository) {
		return (args) -> {
			// save a couple of Employees
			repository.save(new DefaultEmployee("Jakub", "Nowy"));
			repository.save(new DefaultEmployee("Jack", "Bauer"));
			repository.save(new DefaultEmployee("Chloe", "O'Brian"));
			repository.save(new DefaultEmployee("Kim", "Bauer"));
			repository.save(new DefaultEmployee("David", "Palmer"));
			repository.save(new DefaultEmployee("Michelle", "Dessler"));

			// fetch all Employees
			log.info("Employees found with findAll():");
			log.info("-------------------------------");
			for (DefaultEmployee DefaultEmployee : repository.findAll()) {
				log.info(DefaultEmployee.toString());
			}
			log.info("");

			// fetch an individual DefaultEmployee by ID
			repository.findById(1L)
					.ifPresent(DefaultEmployee -> {
						log.info("DefaultEmployee found with findById(1L):");
						log.info("--------------------------------");
						log.info(DefaultEmployee.toString());
						log.info("");
					});

			// fetch Employees by last name
			log.info("DefaultEmployee found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (DefaultEmployee bauer : repository.findByLastName("Bauer")) {
			// 	log.info(bauer.toString());
			// }
			log.info("");
		};
	}
}