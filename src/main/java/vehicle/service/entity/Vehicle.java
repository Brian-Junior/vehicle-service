package vehicle.service.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicalId;
	
	private String vehicleYear;
	private String vehicleMake;
	private String vehicleModel;
	private String vehicleColor;
	
	private Set<Vehicle> vehicle = new HashSet<>();
	

}
