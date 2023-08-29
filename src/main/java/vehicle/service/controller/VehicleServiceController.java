package vehicle.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vehicle.service.controller.model.CustomerData;
import vehicle.service.service.VehicleServiceService;

@RestController
@RequestMapping("/Vehicle_service")
@Slf4j
public class VehicleServiceController {

	@Autowired
	private VehicleServiceService vehicleServiceService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CustomerData insetCustomer(@RequestBody CustomerData customerData) {
		log.info("Creating customer {}", customerData);
		return vehicleServiceService.saveCustomer(customerData);
	}
 
}
