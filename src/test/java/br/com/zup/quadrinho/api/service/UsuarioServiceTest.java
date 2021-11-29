package br.com.zup.quadrinho.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.zup.quadrinho.api.dto.UsuarioDto;
import br.com.zup.quadrinho.api.dto.UsuarioFormDto;
import br.com.zup.quadrinho.api.exception.DadoEmUsoException;
import br.com.zup.quadrinho.api.mapper.UsuarioMapper;
import br.com.zup.quadrinho.api.model.Usuario;
import br.com.zup.quadrinho.api.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private UsuarioMapper usuarioMapper;
	
	@InjectMocks
	private UsuarioService usuarioService;

	@Test
	void deveriaCadastrarUsuarioComDadosValidos() {
		
		UsuarioFormDto usuarioFormDto = new UsuarioFormDto("Antonio","antonio@email.com.br","12345678900", LocalDate.of(2000, 1, 1));
		
		Usuario usuario = new Usuario("Antonio","antonio@email.com.br","12345678900", LocalDate.of(2000, 1, 1));
		
		UsuarioDto usuarioDto = new UsuarioDto(1L,"Antonio","antonio@email.com.br","12345678900", LocalDate.of(2000, 1, 1));
		
		Mockito
		.when(usuarioRepository.existsByEmail(usuarioFormDto.getEmail()))
		.thenReturn(false);
		
		Mockito
		.when(usuarioRepository.existsByCpf(usuarioFormDto.getCpf()))
		.thenReturn(false);
		
		Mockito
		.when(usuarioMapper.toUsuario(usuarioFormDto))
		.thenReturn(usuario);
		
		Mockito
		.when(usuarioMapper.toUsuarioDto(usuario))
		.thenReturn(usuarioDto);
		
		usuarioDto = usuarioService.cadastrar(usuarioFormDto);
		
		Mockito
		.verify(usuarioRepository).save(Mockito.any());
		
		assertEquals(usuarioFormDto.getNome(), usuarioDto.getNome());
		assertEquals(usuarioFormDto.getEmail(), usuarioDto.getEmail());
		assertEquals(usuarioFormDto.getCpf(), usuarioDto.getCpf());
		assertEquals(usuarioFormDto.getDataNascimento(), usuarioDto.getDataNascimento());

	}
	
	@Test
	void naoDeveriaCadastrarUsuarioComEmailRepetido() {
		
		UsuarioFormDto usuarioFormDto = new UsuarioFormDto("Antonio","antonio@email.com.br","12345678900", LocalDate.of(2000, 1, 1));
		
		Mockito
		.when(usuarioRepository.existsByEmail(usuarioFormDto.getEmail()))
		.thenReturn(true);
		
		assertThrows(DadoEmUsoException.class, () -> usuarioService.cadastrar(usuarioFormDto));

	}
	
	@Test
	void naoDeveriaCadastrarUsuarioComCpfRepetido() {
		
		UsuarioFormDto usuarioFormDto = new UsuarioFormDto("Antonio","antonio@email.com.br","12345678900", LocalDate.of(2000, 1, 1));
		
		Mockito
		.when(usuarioRepository.existsByCpf(usuarioFormDto.getCpf()))
		.thenReturn(true);
		
		assertThrows(DadoEmUsoException.class, () -> usuarioService.cadastrar(usuarioFormDto));

	}

}
