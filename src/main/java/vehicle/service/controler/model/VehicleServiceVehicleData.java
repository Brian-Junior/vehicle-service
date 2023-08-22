package vehicle.service.controler.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import vehicle.service.entity.Service;

@Data
@NoArgsConstructor
public class VehicleServiceVehicleData {
	
	private Long vehicleId;
	private String vehicleYear;
	private String vehicleMake;
	private String vehicleModel;
	private String vehicleColor;
	private Set<Service> services = new HashSet<>();

}
