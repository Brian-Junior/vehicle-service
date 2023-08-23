package vehicle.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vehicle.service.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
