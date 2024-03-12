package boteco.tilapia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import boteco.tilapia.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	Optional<Cliente> findByEmail(String email);
	boolean existsByEmail(String email);

}