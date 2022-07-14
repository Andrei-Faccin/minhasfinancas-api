package com.afaccin.minhasFinancas.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.afaccin.minhasFinancas.exception.RegraNegocioException;
import com.afaccin.minhasFinancas.model.repository.UsuarioRepository;
import com.afaccin.minhasFinancas.service.empl.UsuariosServiceEmpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("Test")
public class UsuarioServiceTest {

	UsuarioService service;
	UsuarioRepository repository;
	
	@Before // quer dizer que vai ser executado antes de cada um dos testes
	public void setUp() {
		repository = Mockito.mock(UsuarioRepository.class);
		service = new UsuariosServiceEmpl(repository);
		
	}
	
	@Test(expected = Test.None.class)
	public void DeveValidarEmail() {
		
		//cenario	
		// UsuarioRepository usuarioRepositoryMock =  Mockito.mock(UsuarioRepository.class);  <= exemplo de como criar um mock
		//repository.deleteAll();
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false); // simula que: quando for chamado o metodo "existisByEmail"  passando qualquer string como parametro, ele vai retornar falso
		
		// açao
		service.validarEmail("email@mail.com");
		
	}
	
	@Test(expected = RegraNegocioException.class)
	public void deveLancarErroAoValidarEmailQuandoExistirMailCadastrado() {
		//cenario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
		//Usuario usuario = Usuario.builder().nome("usuario").email("email@email.com").build();
		//repository.save(usuario);
		
		// ação
		service.validarEmail("email@email.com");
	}

}
