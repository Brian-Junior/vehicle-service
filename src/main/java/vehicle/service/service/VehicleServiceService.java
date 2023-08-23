package vehicle.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vehicle.service.dao.CustomerDao;
import vehicle.service.dao.ServiceDao;
import vehicle.service.dao.VehicleDao;

@Service
public class VehicleServiceService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ServiceDao serviceDao;
	
	@Autowired
	private VehicleDao vehicleDao;

}
