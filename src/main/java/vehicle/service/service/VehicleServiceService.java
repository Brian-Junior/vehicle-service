package vehicle.service.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vehicle.service.controller.model.CustomerData;
import vehicle.service.dao.CustomerDao;
import vehicle.service.dao.ServiceDao;
import vehicle.service.dao.VehicleDao;
import vehicle.service.entity.Customer;

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
	
	
}
