package vehicle.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vehicle.service.service.VehicleServiceService;

@RestController
@RequestMapping("/Vehicle_service")
@Slf4j
public class VehicleServiceController {

	@Autowired
	private VehicleServiceService vehicleServiceService;
	
	
}