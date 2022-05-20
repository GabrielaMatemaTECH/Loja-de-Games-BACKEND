package com.generation.lojadegames.lojadegames.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.lojadegames.lojadegames.model.Usuario;

@Repository
public interface usuarioRepository extends JpaRepository <Usuario, Long>{
	
	public Optional<Usuario> findByUsuario (String usuario);

}
