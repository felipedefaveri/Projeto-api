package org.serratec.ecommerce.repository;


import java.util.Optional;

import org.serratec.ecommerce.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	 public Optional<Cliente> findByNome(String nome);
}
