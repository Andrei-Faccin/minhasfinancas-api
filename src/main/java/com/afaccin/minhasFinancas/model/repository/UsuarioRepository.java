package com.afaccin.minhasFinancas.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afaccin.minhasFinancas.model.entity.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
	
	
	// select * from usuario where exists() -> verifica se tem algum usuário com o email, ja cadastrado na base de dados.
	boolean existsByEmail(String email); 
	
	Optional<Usuario> findByEmail(String email);

}
