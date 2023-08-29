package vehicle.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vehicle.service.entity.Repair;

public interface RepairDao extends JpaRepository<Repair, Long> {

}
