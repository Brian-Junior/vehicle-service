package vehicle.service.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	@EqualsAndHashCode.Exclude
	
	private String customerFirstName;
	
	@EqualsAndHashCode.Exclude
	
	private String customerLastName;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude	
	@Column(unique = true)
	private String customerEmail;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	
	private Set<Vehicle> vehicles = new HashSet<>();
	
	
}
