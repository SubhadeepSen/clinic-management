package dr.sens.dental.clinic.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dr.sens.dental.clinic.*")
public class DrSensDentalClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrSensDentalClinicApplication.class, args);
	}
}
