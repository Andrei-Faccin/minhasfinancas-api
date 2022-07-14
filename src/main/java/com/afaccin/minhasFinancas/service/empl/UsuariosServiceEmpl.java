package com.afaccin.minhasFinancas.service.empl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afaccin.minhasFinancas.exception.ErroAutenticacao;
import com.afaccin.minhasFinancas.exception.RegraNegocioException;
import com.afaccin.minhasFinancas.model.entity.Usuario;
import com.afaccin.minhasFinancas.model.repository.UsuarioRepository;
import com.afaccin.minhasFinancas.service.UsuarioService;

@Service
public class UsuariosServiceEmpl implements UsuarioService{
	
	@Autowired // grande candidato a ser mocado nos testar unitarios
	private UsuarioRepository repository;
	

	public UsuariosServiceEmpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não encontrado.");
		}
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha Inválida.");
		}
		return usuario.get();
	}

	@Override
	@Transactional // cria na base de dados uma transação e executa o metodo de salvar usuario e comita depois
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
		}
		
	}

}
