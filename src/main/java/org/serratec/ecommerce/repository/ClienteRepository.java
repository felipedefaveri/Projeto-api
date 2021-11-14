package org.serratec.ecommerce.repository;

import java.util.Optional;

import org.serratec.ecommerce.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Optional<Cliente> findByNome(String nome);
	Optional<Cliente> findByEmail(String email);
}
