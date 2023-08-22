package vehicle.service.controler.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import vehicle.service.entity.Vehicle;

@Data
@NoArgsConstructor
public class VehicleServiceServiceData {
	
	
	private Long serviceId;
	private String serviceType;
	private BigDecimal serviceCost;
	private Set<Vehicle> vehicles = new HashSet<>();
}
