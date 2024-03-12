package boteco.tilapia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import boteco.tilapia.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	@Query(value = "select * from pedido", nativeQuery = true)
    public Page<Pedido> findAllPedido(Pageable page);

}