package vehicle.service.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vehicle.service.controller.model.CustomerData;
import vehicle.service.controller.model.RepairData;
import vehicle.service.controller.model.VehicleData;
import vehicle.service.dao.CustomerDao;
import vehicle.service.dao.RepairDao;
import vehicle.service.dao.VehicleDao;
import vehicle.service.entity.Customer;
import vehicle.service.entity.Repair;
import vehicle.service.entity.Vehicle;

@Service
public class VehicleServiceService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private RepairDao repairDao;
	
	@Autowired
	private VehicleDao vehicleDao;

//SAVE CUSTOMER DATA	
	@Transactional(readOnly = false)
	public CustomerData saveCustomer(CustomerData vehicleServiceCustomerData) {
		Long customerId = vehicleServiceCustomerData.getCustomerId();
		Customer customer = findOrCreateCustomer(customerId);
		
		setFieldsInCustomer(customer, vehicleServiceCustomerData);
		return new CustomerData(customerDao.save(customer));
	}
	private void setFieldsInCustomer(Customer customer, CustomerData vehicleServiceCustomerData) {
		customer.setCustomerId(vehicleServiceCustomerData.getCustomerId());
		customer.setCustomerFirstName(vehicleServiceCustomerData.getCustomerFirstName());
		customer.setCustomerLastName(vehicleServiceCustomerData.getCustomerLastName());
		customer.setCustomerEmail(vehicleServiceCustomerData.getCustomerEmail());
		
	}	
private Customer findOrCreateCustomer(Long customerId) {
		
		if (Objects.isNull(customerId)) {
			return new Customer();
		} else {
			return findCustomerById(customerId);
		}
	}

private Customer findCustomerById(Long customerId) {

	return customerDao.findById(customerId)
			.orElseThrow(() -> new NoSuchElementException("Customer with ID =" + customerId + " was not found"));

	}
//SAVE VEHICLE DATA
@Transactional(readOnly = false)
public VehicleData saveVehicle(Long customerId, VehicleData vehicleData) {


 Customer customer = findCustomerById(customerId);
 Long vehicleId = vehicleData.getVehicleId();
 Vehicle vehicle = findOrCreateVehicle( customerId,vehicleId);
 
 copyVehicleFields(vehicle, vehicleData);
 vehicle.setCustomer(customer);
 customer.getVehicles().add(vehicle);
 return new VehicleData(vehicleDao.save(vehicle));
  

}
private void copyVehicleFields(Vehicle vehicle, VehicleData vehicleData) {
	vehicle.setVehicleId(vehicleData.getVehicleId());
	vehicle.setVehicleYear(vehicleData.getVehicleYear());
	vehicle.setVehicleMake(vehicleData.getVehicleMake());
	vehicle.setVehicleModel(vehicleData.getVehicleModel());
	vehicle.setVehicleColor(vehicleData.getVehicleColor());


}
//FIND OR CREATE VEHICLE
private Vehicle findOrCreateVehicle( Long customerId,Long vehicleId) {
if (Objects.isNull(vehicleId)){
	return new Vehicle();
} else {
	return findVehicleById(customerId,vehicleId);
}
}

//FIND VEHICLE BY ID
private Vehicle findVehicleById(Long customerId, Long vehicleId) {

	Vehicle vehicle = vehicleDao.findById(vehicleId)
			.orElseThrow(() -> new NoSuchElementException("Vehicle with ID =" + vehicleId + " was not found"));

if (vehicle.getCustomer().getCustomerId()!= customerId) {
	throw new IllegalArgumentException("vehicle "  + vehicleId + " does not belong to customer" + customerId);
}
return vehicle;


}
//RETREVE ALL CUSTOMERS
@Transactional(readOnly = true)
public List<CustomerData> retrieveAllCustomers(){
	List<Customer> customers = customerDao.findAll();
	List<CustomerData> result = new LinkedList<>();
	for (Customer customer : customers) {
		CustomerData psd = new CustomerData(customer);
				
				//psd.getRepairs().clear();
				psd.getVehicles().clear();
				
				result.add(psd);	
}
			
	return result;
	
}
// SAVE REPAIR DATA
@Transactional(readOnly = false)
public RepairData saveRepair(Long customerId, Long vehicleId, RepairData repairData) {


	 Vehicle vehicle = findVehicleById( customerId,vehicleId);
	 Long repairId = repairData.getRepairId();
	 Repair repair = findOrCreateRepair(vehicleId, repairId);
	 
	 copyRepairFields(repair, repairData);
	 repair.getVehicles().add(vehicle);
	vehicle.getRepairs().add(repair);
	 return new RepairData(repairDao.save(repair));
	  	
}

private void copyRepairFields(Repair repair, RepairData repairData) {
	repair.setRepairId(repairData.getRepairId());
	repair.setRepairType(repairData.getRepairType());
	repair.setRepairCost(repairData.getRepairCost());
	
}
//FIND OR CREATE REPAIR
private Repair findOrCreateRepair(Long vehicleId, Long repairId) {
	if (Objects.isNull(repairId)){
		return new Repair();
	} else {
		return findRepairById(vehicleId, repairId);
	}
}
	
//FIND REPAIR BY ID
private Repair findRepairById(Long vehicleId, Long repairId) {
	Repair repair = repairDao.findById(repairId).orElseThrow(() -> 
	 new NoSuchElementException("No repairs found"));
	boolean flag = false;
	for (Vehicle vehicle : repair.getVehicles()) {
	if (vehicle.getVehicleId()== vehicleId) {
		flag = true;
		break;
	}
	}
	if (!flag) {
	
		throw new IllegalArgumentException("Repair with id" + repairId + " is not avalible ");
	}
	return repair;
	}
// GET CUSTOMER BY ID
@Transactional(readOnly = true)
public CustomerData retrieveCustomerById(Long customerId) {
	Customer customer = findCustomerById(customerId);
	return new CustomerData(customer);
}
// GET VEHICLE BY ID
@Transactional(readOnly = true)
public VehicleData retrieveVehicleById(Long customerId, Long vehicleId) {
	Vehicle vehicle = findVehicleById(customerId, vehicleId);
	return new VehicleData(vehicle);
}
//DELETE CUSTOMER BY ID
@Transactional(readOnly = false)
public void deleteCustomerById(Long customerId) {
	Customer customer = findCustomerById(customerId);
	customerDao.delete(customer);
}
// DELETE VEHICLE BY ID
@Transactional(readOnly = false)
public void deleteVehicleById(Long customerId, Long vehicleId) {
	Vehicle vehicle = findVehicleById(customerId, vehicleId);
	vehicleDao.delete(vehicle);
	
	
}
}
