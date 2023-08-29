package vehicle.service.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Repair {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long repairId;
	
	@EqualsAndHashCode.Exclude
	
	private String repairType;
	
	@EqualsAndHashCode.Exclude
	
	private BigDecimal repairCost;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "services")
		private Set<Vehicle> vehicles = new HashSet<>();
}
