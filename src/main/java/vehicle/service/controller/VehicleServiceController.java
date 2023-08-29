package vehicle.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vehicle.service.controller.model.CustomerData;
import vehicle.service.controller.model.ServiceData;
import vehicle.service.controller.model.VehicleData;
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
	@PutMapping("/{customerId}")
	public CustomerData updateCustomerData(@PathVariable Long customerId, @RequestBody CustomerData customerData) {
		customerData.setCustomerId(customerId);
		log.info("Updating customer{}", customerData);
		return vehicleServiceService.saveCustomer(customerData);
	}
	@PostMapping("/{customerId}/vehicle")
	@ResponseStatus(code = HttpStatus.CREATED)
	public VehicleData saveVehicle(@PathVariable Long customerId, @RequestBody VehicleData vehicleData) {
		log.info("Creating Vehicle {}", vehicleData);
		return vehicleServiceService.saveVehicle(customerId, vehicleData);
	} 
	@GetMapping
	public List<CustomerData> retrieveAllCustomers(){
		log.info("Showing all customers ");
		return vehicleServiceService.retrieveAllCustomers();
	}
	
	@PostMapping("/{customerId}/service")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ServiceData saveService(@PathVariable Long customerId, @RequestBody ServiceData serviceData) {
		log.info("Creating service {}", serviceData);
		return vehicleServiceService.saveService(customerId, serviceData);
	}
	@GetMapping("/{customerId}")
	public CustomerData retrieveCustomerById(@PathVariable Long customerId) {
		log.info("Retriving customer with ID={}", customerId);
		return vehicleServiceService.retrieveCustomerById(customerId);
	}
	@DeleteMapping("/{customerId}")
	public Map<String, String> deleteCustomerById(@PathVariable Long customerId ){
		log.info("Deleting customer with id=" + customerId + ".");
		vehicleServiceService.deleteCustomerById(customerId);
		
		return Map.of("message", "Customer with ID=" + customerId + "was deleted successfully");
	}
}
