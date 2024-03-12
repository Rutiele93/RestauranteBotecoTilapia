package boteco.tilapia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import boteco.tilapia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}