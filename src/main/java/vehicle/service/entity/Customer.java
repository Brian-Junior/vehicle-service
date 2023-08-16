package vehicle.service.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	private String customerFirstName;
	
	private String customerLastName;
	
	@Column(unique = true)
	private String customerEmail;
	
	@OneToMany()
	private Set<Customer> customer = new HashSet<>();
}
