package vehicle.service.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import vehicle.service.entity.Customer;
import vehicle.service.entity.Repair;
import vehicle.service.entity.Vehicle;


@Data
@NoArgsConstructor
public class CustomerData {

		private Long customerId;	
		private String customerFirstName;
		private String customerLastName;
		private String customerEmail;
		private Set<VehicleData> vehicles = new HashSet<>();
		private Set<RepairData> repairs = new HashSet<>();
	
		
		public CustomerData (Customer customer) {
			customerId = customer.getCustomerId();
			customerFirstName = customer.getCustomerFirstName();
			customerLastName = customer.getCustomerLastName();
			customerEmail = customer.getCustomerEmail();
			
			for(Vehicle vehicle : customer.getVehicles() ) {
				vehicles.add(new VehicleData(vehicle));
			}
			for (Repair repair : customer.getRepairs() ) {
				repairs.add( new RepairData(repair));
			}
		}
		
		
}
