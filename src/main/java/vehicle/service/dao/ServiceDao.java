package vehicle.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vehicle.service.entity.Service;

public interface ServiceDao extends JpaRepository<Service, Long> {

}
