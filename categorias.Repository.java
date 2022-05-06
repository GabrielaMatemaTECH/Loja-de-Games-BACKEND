package com.generation.lojadegames.lojadegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.generation.lojadegames.lojadegames.model.Categoria;

public interface categoriasRepository extends JpaRepository <Categoria, Long>{
	public List <Categoria> findAllByTipoContainingIgnoreCase(@Param("tipo") String tipo);
}
© 2022 GitHub, Inc.
Terms
Privacy
Security
Status
Docs
C
