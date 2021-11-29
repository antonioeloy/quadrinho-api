package br.com.zup.quadrinho.api.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.zup.quadrinho.api.model.Autor;
import br.com.zup.quadrinho.api.model.IsbnData;
import br.com.zup.quadrinho.api.model.PrecoQuadrinho;
import br.com.zup.quadrinho.api.model.Quadrinho;
import br.com.zup.quadrinho.api.model.Usuario;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private QuadrinhoRepository quadrinhoRepository;

	@Test
	void deveriaCadastrarUsuarioComDadosValidos() {
		
		Usuario usuario = new Usuario("Antonio","antonio@email.com.br","12345678900", LocalDate.of(2000, 1, 1));
		
		Usuario usuarioCadastrado = usuarioRepository.save(usuario);
		
		assertTrue(usuarioRepository.existsById(usuarioCadastrado.getId()));
		
		assertEquals(usuario.getNome(), usuarioCadastrado.getNome());
		assertEquals(usuario.getEmail(), usuarioCadastrado.getEmail());
		assertEquals(usuario.getCpf(), usuarioCadastrado.getCpf());
		assertEquals(usuario.getDataNascimento(), usuarioCadastrado.getDataNascimento());
		
	}
	
	@Test
	void deveriaCadastrarQuadrinhoComDadosValidos() {
		
		Quadrinho quadrinho = new Quadrinho(1306, "Título", new IsbnData("123456789"), "Descricao");
		
		PrecoQuadrinho preco = new PrecoQuadrinho("Tipo", new BigDecimal("10.00"));
		preco.setQuadrinho(quadrinho);
		quadrinho.adicionaPreco(preco);
		
		Autor autor1 = new Autor("URI 1", "Autor 1");
		Autor autor2 = new Autor("URI 2", "Autor 2");
		quadrinho.adicionaAutor(autor1);
		quadrinho.adicionaAutor(autor2);
		
		Quadrinho quadrinhoCadastrado = quadrinhoRepository.save(quadrinho);
		
		assertTrue(quadrinhoRepository.existsById(quadrinhoCadastrado.getId()));
		
		assertEquals(quadrinho.getComicId(), quadrinhoCadastrado.getComicId());
		assertEquals(quadrinho.getTitulo(), quadrinhoCadastrado.getTitulo());
		assertEquals(quadrinho.getIsbnData(), quadrinhoCadastrado.getIsbnData());
		assertEquals(quadrinho.getDescricao(), quadrinhoCadastrado.getDescricao());
		assertEquals(quadrinho.getPrecos().size(), quadrinhoCadastrado.getPrecos().size());
		assertEquals(quadrinho.getPrecos().get(0), quadrinhoCadastrado.getPrecos().get(0));
		assertEquals(quadrinho.getAutores().size(), quadrinhoCadastrado.getAutores().size());
		assertEquals(quadrinho.getAutores().get(0), quadrinhoCadastrado.getAutores().get(0));
		assertEquals(quadrinho.getAutores().get(1), quadrinhoCadastrado.getAutores().get(1));
		
	}
	
	@Test
	void naoDeveriaCadastrarQuadrinhoSemTitulo() {
		
		Quadrinho quadrinho = new Quadrinho(1306, null, new IsbnData("123456789"), "Descricao");
		
		PrecoQuadrinho preco = new PrecoQuadrinho("Tipo", new BigDecimal("10.00"));
		preco.setQuadrinho(quadrinho);
		quadrinho.adicionaPreco(preco);
		
		Autor autor1 = new Autor("URI 1", "Autor 1");
		Autor autor2 = new Autor("URI 2", "Autor 2");
		quadrinho.adicionaAutor(autor1);
		quadrinho.adicionaAutor(autor2);
		
		assertThrows(ConstraintViolationException.class, () -> quadrinhoRepository.save(quadrinho));
		
	}
	
	@Test
	void naoDeveriaCadastrarQuadrinhoSemPreco() {
		
		Quadrinho quadrinho = new Quadrinho(1306, "Título", new IsbnData("123456789"), "Descricao");
		
		Autor autor1 = new Autor("URI 1", "Autor 1");
		Autor autor2 = new Autor("URI 2", "Autor 2");
		quadrinho.adicionaAutor(autor1);
		quadrinho.adicionaAutor(autor2);
		
		assertThrows(ConstraintViolationException.class, () -> quadrinhoRepository.save(quadrinho));
		
	}
	
	@Test
	void naoDeveriaCadastrarQuadrinhoSemAutor() {
		
		Quadrinho quadrinho = new Quadrinho(1306, "Título", new IsbnData("123456789"), "Descricao");
		
		PrecoQuadrinho preco = new PrecoQuadrinho("Tipo", new BigDecimal("10.00"));
		preco.setQuadrinho(quadrinho);
		quadrinho.adicionaPreco(preco);
		
		assertThrows(ConstraintViolationException.class, () -> quadrinhoRepository.save(quadrinho));
		
	}
	
	@Test
	void naoDeveriaCadastrarQuadrinhoSemIsbn() {
		
		Quadrinho quadrinho = new Quadrinho(1306, "Título", null, "Descricao");
		
		PrecoQuadrinho preco = new PrecoQuadrinho("Tipo", new BigDecimal("10.00"));
		preco.setQuadrinho(quadrinho);
		quadrinho.adicionaPreco(preco);
		
		Autor autor1 = new Autor("URI 1", "Autor 1");
		Autor autor2 = new Autor("URI 2", "Autor 2");
		quadrinho.adicionaAutor(autor1);
		quadrinho.adicionaAutor(autor2);
		
		assertThrows(ConstraintViolationException.class, () -> quadrinhoRepository.save(quadrinho));
		
	}

}
