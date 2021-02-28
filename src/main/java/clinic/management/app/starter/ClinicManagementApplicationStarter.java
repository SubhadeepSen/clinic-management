package clinic.management.app.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "clinic.management.*")
public class ClinicManagementApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(ClinicManagementApplicationStarter.class, args);
	}
}