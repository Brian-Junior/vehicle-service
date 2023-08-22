package vehicle.service.controler.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import vehicle.service.entity.Service;

@Data
@NoArgsConstructor
public class VehicleServiceService {
	
	
	private Long serviceId;
	private String serviceType;
	private BigDecimal serviceCost;
	
	public VehicleServiceService (Service service) {
		serviceId = service.getServiceId();
		serviceType = service.getServiceType();
		serviceCost = service.getServiceCost();
	}
}
