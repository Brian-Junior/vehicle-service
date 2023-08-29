package vehicle.service.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vehicle.service.controller.model.CustomerData;
import vehicle.service.controller.model.ServiceData;
import vehicle.service.controller.model.VehicleData;
import vehicle.service.dao.CustomerDao;
import vehicle.service.dao.ServiceDao;
import vehicle.service.dao.VehicleDao;
import vehicle.service.entity.Customer;
import vehicle.service.entity.Vehicle;

@Service
public class VehicleServiceService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ServiceDao serviceDao;
	
	@Autowired
	private VehicleDao vehicleDao;

	
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

@Transactional(readOnly = false)
public VehicleData saveVehicle(Long customerId, VehicleData vehicleData) {


 Customer customer = findCustomerById(customerId);
 Long vehicleId = vehicleData.getVehicleId();
 Vehicle vehicle = findOrCreateVehicle(customerId, vehicleId);
 
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

private Vehicle findOrCreateVehicle(Long customerId, Long vehicleId) {
if (Objects.isNull(vehicleId)){
	return new Vehicle();
} else {
	return findVehicleById(customerId,vehicleId);
}
}


private Vehicle findVehicleById(Long customerId, Long vehicleId) {
	Vehicle vehicle = vehicleDao.findById(vehicleId).orElseThrow(() -> 
 new NoSuchElementException("No Vehicle found"));
if (vehicle.getCustomer().getCustomerId()!= customerId) {
	throw new IllegalArgumentException("Vehicle with id" + vehicleId + " does not exist");
}
return vehicle;

}
@Transactional(readOnly = true)
public List<CustomerData> retrieveAllCustomers(){
	List<Customer> customers = customerDao.findAll();
	List<CustomerData> result = new LinkedList<>();
	for (Customer customer : customers) {
		CustomerData psd = new CustomerData(customer);
				
				psd.getServices().clear();
				psd.getVehicles().clear();
				
				result.add(psd);	
}
			
	return result;
	
}

@Transactional(readOnly = false)
public ServiceData saveService(Long vehicleId, ServiceData serviceData) {


	 Customer customer = findCustomerById(customerId);
	 Long serviceId = serviceData.getServiceId();
	 Service service = findOrCreateService(vehicleId, serviceId);
	 
	 copyServiceFields(service, serviceData);
	 service.getvehicles().add(vehicle);
	customer.getServices().add(service);
	 return new ServiceData(serviceDao.save(service));
	  	
}

private void copyServiceFields(Service service, ServiceData serviceData) {
	service.setServiceId(serviceData.getServiceId());
	service.setServiceType(serviceData.getServiceType());
	service.setServiceCost(serviceData.getServiceCost());
	
}
private Customer findOrCreateService(Long customerId, Long serviceId) {
	if (Objects.isNull(serviceId)){
		return new Service();
	} else {
		return findServiceById(customerId, serviceId);
	}
}
	

private Service findServiceById(Long customerId, Long serviceId) {
	Service service = serviceDao.findById(serviceId).orElseThrow(() -> 
	 new NoSuchElementException("No services found"));
	boolean flag = false;
	for (Customer customer : service.getCustomers()) {
	if (customer.getCustomerId()== customerId) {
		flag = true;
		break;
	}
	}
	if (!flag) {
	
		throw new IllegalArgumentException("Service with id" + serviceId + " is not avalible ");
	}
	return service;
	}

@Transactional(readOnly = true)
public CustomerData retrieveCustomerById(Long customerId) {
	Customer customer = findCustomerById(customerId);
	return new CustomerData(customer);
}


@Transactional(readOnly = false)
public void deleteCustomerById(Long customerId) {
	Customer customer = findCustomerById(customerId);
	customerDao.delete(customer);
	
	
}	
}
