package dr.sens.dental.clinic.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dr.sens.dental.clinic.*")
public class ClinicManagementApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(ClinicManagementApplicationStarter.class, args);
	}
}
