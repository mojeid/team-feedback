package teamfeedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import teamfeedback.Employee;
import teamfeedback.EmployeeRepository;

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
			repository.save(new Employee("Jack", "Bauer"));
			repository.save(new Employee("Chloe", "O'Brian"));
			repository.save(new Employee("Kim", "Bauer"));
			repository.save(new Employee("David", "Palmer"));
			repository.save(new Employee("Michelle", "Dessler"));

			// fetch all Employees
			log.info("Employees found with findAll():");
			log.info("-------------------------------");
			for (teamfeedback.Employee Employee : repository.findAll()) {
				log.info(Employee.toString());
			}
			log.info("");

			// fetch an individual Employee by ID
			repository.findById(1L)
					.ifPresent(Employee -> {
						log.info("Employee found with findById(1L):");
						log.info("--------------------------------");
						log.info(Employee.toString());
						log.info("");
					});

			// fetch Employees by last name
			log.info("Employee found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Employee bauer : repository.findByLastName("Bauer")) {
			// 	log.info(bauer.toString());
			// }
			log.info("");
		};
	}
}