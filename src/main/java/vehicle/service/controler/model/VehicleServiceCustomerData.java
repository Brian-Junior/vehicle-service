package vehicle.service.controler.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import vehicle.service.entity.Customer;
import vehicle.service.entity.Service;
import vehicle.service.entity.Vehicle;


@Data
@NoArgsConstructor
public class VehicleServiceCustomerData {

		private Long customerId;	
		private String customerFirstName;
		private String customerLastName;
		private String customerEmail;
		private Set<VehicleServiceVehicle> vehicles = new HashSet<>();
		private Set<VehicleServiceService> services = new HashSet<>();
	
		
		public VehicleServiceCustomerData (Customer customer) {
			customerId = customer.getCustomerId();
			customerFirstName = customer.getCustomerFirstName();
			customerLastName = customer.getCustomerLastName();
			customerEmail = customer.getCustomerEmail();
			
			for(Vehicle vehicle : customer.getVehicles() ) {
				vehicle.add(new CustomerVehicle(vehicle));
			}
			for (Service service : customer.getServices() ) {
				service.add( new CustomerService(service));
			}
		}
		@Data
		@NoArgsConstructor
		public static class CustomerVehicle{
			private Long vehicleId;
			private String vehicleYear;
			private String vehicleMake;
			private String vehicleModel;
			private String vehicleColor;
			
		public CustomerVehicle (Vehicle vehicle) {
			vehicleId = vehicle.getVehicleId();
			vehicleYear = vehicle.getVehicleYear();
			vehicleMake = vehicle.getVehicleMake();
			vehicleModel = vehicle.getVehicleModel();
			vehicleColor = vehicle.getVehicleColor();
			
		}
		}
		@Data
		@NoArgsConstructor
		public static class CustomerService {
			private Long serviceId;
			private String serviceType;
			private BigDecimal serviceCost;
			
		
		public CustomerService 	(Service service) {
			serviceId = service.getServiceId();
			serviceType = service.getServiceType();
			serviceCost = service.getServiceCost();
			
		}
		}
		
}
