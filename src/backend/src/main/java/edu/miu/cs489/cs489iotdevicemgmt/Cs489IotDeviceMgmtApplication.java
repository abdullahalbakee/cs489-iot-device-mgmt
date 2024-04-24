package edu.miu.cs489.cs489iotdevicemgmt;

import edu.miu.cs489.cs489iotdevicemgmt.service.AddressService;
import edu.miu.cs489.cs489iotdevicemgmt.service.DeviceService;
import edu.miu.cs489.cs489iotdevicemgmt.service.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Cs489IotDeviceMgmtApplication implements CommandLineRunner {

	private final AddressService addressService;
	private final ClientService clientService;
	private final DeviceService deviceService;

	public Cs489IotDeviceMgmtApplication(AddressService addressService, ClientService clientService, DeviceService deviceService) {
		this.addressService = addressService;
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
