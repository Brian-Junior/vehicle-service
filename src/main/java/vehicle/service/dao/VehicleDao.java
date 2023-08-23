package vehicle.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vehicle.service.entity.Vehicle;

public interface VehicleDao extends JpaRepository<Vehicle, Long> {

}
