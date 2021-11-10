package org.serratec.ecommerce.repository;

import java.util.Optional;

import org.serratec.ecommerce.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	Optional<Categoria> findByNome(String nome);
	boolean existsByNome(String nome);
	void deleteByNome(String nome);
}
