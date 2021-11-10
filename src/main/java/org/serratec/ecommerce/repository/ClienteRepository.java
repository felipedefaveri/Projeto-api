package org.serratec.ecommerce.repository;

import org.serratec.ecommerce.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
