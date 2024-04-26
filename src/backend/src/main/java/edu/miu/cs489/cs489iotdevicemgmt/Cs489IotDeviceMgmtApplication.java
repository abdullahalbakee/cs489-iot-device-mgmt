package edu.miu.cs489.cs489iotdevicemgmt;

import edu.miu.cs489.cs489iotdevicemgmt.model.Address;
import edu.miu.cs489.cs489iotdevicemgmt.model.User;
import edu.miu.cs489.cs489iotdevicemgmt.repository.UserRepository;
import edu.miu.cs489.cs489iotdevicemgmt.service.DeviceService;
import edu.miu.cs489.cs489iotdevicemgmt.service.ClientService;
import edu.miu.cs489.cs489iotdevicemgmt.service.security.Roles;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Cs489IotDeviceMgmtApplication implements CommandLineRunner {

	private final ClientService clientService;
	private final DeviceService deviceService;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public Cs489IotDeviceMgmtApplication(ClientService clientService, DeviceService deviceService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.clientService = clientService;
        this.deviceService = deviceService;
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
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
		createAdmin();
	}

	private void createAdmin() {
		var existingAdmin = userRepository.findUserByUsername("admin").orElse(null);
		if (existingAdmin != null) return;
		var adminUser = User.builder()
				.username("admin")
				.password(passwordEncoder.encode("admin"))
				.role(Roles.ADMIN)
				.build();
		userRepository.save(adminUser);
	}
}
