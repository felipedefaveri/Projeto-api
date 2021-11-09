package org.serratec.ecommerce.repository;

import org.serratec.ecommerce.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
