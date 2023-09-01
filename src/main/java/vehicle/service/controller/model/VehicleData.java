package vehicle.service.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import vehicle.service.entity.Vehicle;

@Data
@NoArgsConstructor
public class VehicleData {
	
	private Long vehicleId;
	private String vehicleYear;
	private String vehicleMake;
	private String vehicleModel;
	private String vehicleColor;
	
	private Set<RepairData> repairs = new HashSet<>(); 
	
	public VehicleData (Vehicle vehicle) {
		vehicleId = vehicle.getVehicleId();
		vehicleYear = vehicle.getVehicleYear();
		vehicleMake = vehicle.getVehicleMake();
		vehicleModel = vehicle.getVehicleModel();
		vehicleColor = vehicle.getVehicleColor();
	}

}
