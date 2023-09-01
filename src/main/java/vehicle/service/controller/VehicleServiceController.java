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
import vehicle.service.controller.model.RepairData;
import vehicle.service.controller.model.VehicleData;
import vehicle.service.service.VehicleServiceService;

@RestController
@RequestMapping("/Vehicle_service")
@Slf4j
public class VehicleServiceController {

	@Autowired
	private VehicleServiceService vehicleServiceService;
//CREATE NEW CUSTOMER	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CustomerData insetCustomer(@RequestBody CustomerData customerData) {
		log.info("Creating customer {}", customerData);
		return vehicleServiceService.saveCustomer(customerData);
//UPDATE NEW CUSTOMER		
	}
	@PutMapping("/customer/{customerId}")
	public CustomerData updateCustomerData(@PathVariable Long customerId, @RequestBody CustomerData customerData) {
		customerData.setCustomerId(customerId);
		log.info("Updating customer{}", customerData);
		return vehicleServiceService.saveCustomer(customerData);
//CREATE NEW VEHICLE IN CUSTOMER		
	}
	@PostMapping("/customer/{customerId}/vehicle")
	@ResponseStatus(code = HttpStatus.CREATED)
	public VehicleData saveVehicle(@PathVariable Long customerId, @RequestBody VehicleData vehicleData) {
		log.info("Creating Vehicle {}", vehicleData);
		return vehicleServiceService.saveVehicle(customerId, vehicleData);
//RETREVE ALL CUSTOMERS		
	} 
	@GetMapping
	public List<CustomerData> retrieveAllCustomers(){
		log.info("Showing all customers ");
		return vehicleServiceService.retrieveAllCustomers();
	}
//CREATE REPAIR	
	@PostMapping("{customerId}/vehicle/{vehicleId}/repair")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RepairData saveRepair(@PathVariable Long customerId,@PathVariable Long vehicleId, @RequestBody RepairData repairData) {
		log.info("Creating repair {}", repairData);
		return vehicleServiceService.saveRepair(customerId, vehicleId, repairData);
	}
//RETRIVE CUSTOMER BY ID	
	@GetMapping("/customer/{customerId}")
	public CustomerData retrieveCustomerById(@PathVariable Long customerId) {
		log.info("Retriving customer with ID={}", customerId);
		return vehicleServiceService.retrieveCustomerById(customerId);
	}
//DELETE A CUSTOMER BY ID	
	@DeleteMapping("/customer/{customerId}")
	public Map<String, String> deleteCustomerById(@PathVariable Long customerId ){
		log.info("Deleting customer with id=" + customerId + ".");
		vehicleServiceService.deleteCustomerById(customerId);
		
		return Map.of("message", "Customer with ID=" + customerId + "was deleted successfully");
	}
//UPDATE A VEHICLE BY ID	
	@PutMapping("/vehicle/{vehicleId}")
	public VehicleData updatevehicleData(@PathVariable Long vehicleId, @RequestBody VehicleData vehicleData) {
		vehicleData.setVehicleId(vehicleId);
		log.info("Updating vehicle{}", vehicleData);
		return vehicleServiceService.saveVehicle(vehicleId, vehicleData);
	}
//GET VEHICLE BY ID	
	@GetMapping("{customerId}/vehicle/{vehicleId}")
	public VehicleData retrieveVehicleById(@PathVariable Long customerId, @PathVariable Long vehicleId ) {
		log.info("Retriving vehicle with ID={}", vehicleId);
		return vehicleServiceService.retrieveVehicleById(customerId,vehicleId);
	}
//DELETE VEHICLE BY ID	
	@DeleteMapping("/{customerId}/vehicle/{vehicleId}")
	public Map<String, String> deleteVehicleById(@PathVariable Long customerId,@PathVariable Long vehicleId ){
		log.info("Deleting vehicle with id=" + vehicleId + ".");
		vehicleServiceService.deleteVehicleById(customerId, vehicleId);
		
		return Map.of("message", "Vehicle with ID=" + vehicleId + "was deleted successfully");
	}
}
