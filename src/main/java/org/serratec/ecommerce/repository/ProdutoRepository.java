package org.serratec.ecommerce.repository;

import org.serratec.ecommerce.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
