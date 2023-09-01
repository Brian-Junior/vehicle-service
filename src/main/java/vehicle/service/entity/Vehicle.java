package vehicle.service.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicleId;
	
	@EqualsAndHashCode.Exclude
	private String vehicleYear;
	
	@EqualsAndHashCode.Exclude
	private String vehicleMake;
	
	@EqualsAndHashCode.Exclude
	private String vehicleModel;
	
	@EqualsAndHashCode.Exclude
	private String vehicleColor;
	
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "vehicle_repair", joinColumns = @JoinColumn(name = "vehicle_id"),
	inverseJoinColumns = @JoinColumn(name = "repair_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Repair> repairs = new HashSet<>();
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer ;
	
	

}
