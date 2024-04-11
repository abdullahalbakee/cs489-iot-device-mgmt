package edu.miu.cs489.cs489iotdevicemgmt;

import edu.miu.cs489.cs489iotdevicemgmt.model.Address;
import edu.miu.cs489.cs489iotdevicemgmt.model.Device;
import edu.miu.cs489.cs489iotdevicemgmt.model.User;
import edu.miu.cs489.cs489iotdevicemgmt.service.AddressService;
import edu.miu.cs489.cs489iotdevicemgmt.service.DeviceService;
import edu.miu.cs489.cs489iotdevicemgmt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Cs489IotDeviceMgmtApplication implements CommandLineRunner {

	private final AddressService addressService;
	private final UserService userService;
	private final DeviceService deviceService;

	public Cs489IotDeviceMgmtApplication(AddressService addressService, UserService userService, DeviceService deviceService) {
		this.addressService = addressService;
        this.userService = userService;
        this.deviceService = deviceService;
    }

	public static void main(String[] args) {
		SpringApplication.run(Cs489IotDeviceMgmtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var address1 = Address.builder()
				.firstLine("1000 N 4th St")
				.secondLine("")
				.city("Fairfield")
				.state("IA")
				.zip("52557")
				.country("USA")
				.build();
		var createdAddress = addressService.create(address1);
		var user1 = User.builder()
				.firstName("John")
				.lastName("Doe")
				.address(createdAddress)
				.build();
		var createdUser = userService.create(user1);

		var device1 = Device.builder()
				.name("Device 1")
				.serialNumber("1234567890")
				.user(createdUser)
				.address(createdAddress)
				.build();
		var createdDevice = deviceService.create(device1);
		System.out.println(createdDevice);
	}
}