package pt.vwds.truckmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("pt.vwds.truckmonitor.model")
@EnableJpaRepositories("pt.vwds.truckmonitor.repository")
public class TruckMonitorApplication {
	public static void main(String[] args) {
		SpringApplication.run(TruckMonitorApplication.class, args);
	}
}
