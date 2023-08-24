package vehicle.service.controller.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import vehicle.service.entity.Service;

@Data
@NoArgsConstructor
public class ServiceData {
	
	
	private Long serviceId;
	private String serviceType;
	private BigDecimal serviceCost;
	
	public ServiceData (Service service) {
		serviceId = service.getServiceId();
		serviceType = service.getServiceType();
		serviceCost = service.getServiceCost();
	}
}
