package vehicle.service.controller.model;

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
	
	
	public VehicleData (Vehicle vehicle) {
		vehicleId = vehicle.getVehicleId();
		vehicleYear = vehicle.getVehicleYear();
		vehicleMake = vehicle.getVehicleMake();
		vehicleModel = vehicle.getVehicleModel();
		vehicleColor = vehicle.getVehicleColor();
	}

}
