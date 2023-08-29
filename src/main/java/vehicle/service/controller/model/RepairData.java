package vehicle.service.controller.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import vehicle.service.entity.Repair;

@Data
@NoArgsConstructor
public class RepairData {
	
	
	private Long repairId;
	private String repairType;
	private BigDecimal repairCost;
	
	public RepairData (Repair repair) {
		repairId = repair.getRepairId();
		repairType = repair.getRepairType();
		repairCost = repair.getRepairCost();
	}
}
