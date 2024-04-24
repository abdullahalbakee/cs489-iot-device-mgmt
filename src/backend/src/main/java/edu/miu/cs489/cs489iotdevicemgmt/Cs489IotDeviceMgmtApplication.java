package edu.miu.cs489.cs489iotdevicemgmt;

import edu.miu.cs489.cs489iotdevicemgmt.service.DeviceService;
import edu.miu.cs489.cs489iotdevicemgmt.service.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Cs489IotDeviceMgmtApplication implements CommandLineRunner {

	private final ClientService clientService;
	private final DeviceService deviceService;

	public Cs489IotDeviceMgmtApplication(ClientService clientService, DeviceService deviceService) {
        this.clientService = clientService;
        this.deviceService = deviceService;
    }

	public static void main(String[] args) {
		SpringApplication.run(Cs489IotDeviceMgmtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		seedData();
	}

	private void seedData() {
		System.out.println("Seeding initial data into the db...");
	}
}
