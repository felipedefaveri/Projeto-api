package org.serratec.ecommerce.repository;

import org.serratec.ecommerce.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
