package boteco.tilapia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import boteco.tilapia.model.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

	 Optional<Vendedor> findByEmail(String email);
	 boolean existsByEmail(String email);
}