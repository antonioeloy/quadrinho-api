package br.com.zup.quadrinho.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.quadrinho.api.model.Usuario;
import br.com.zup.quadrinho.api.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	void deveriaCadastrarUsuarioComDadosValidos() throws Exception {
		
		String jsonRequest = "{\"nome\": \"Usuario\", "
				+ "\"email\": \"usuario@email.com.br\", "
				+ "\"cpf\": \"08277450010\", "
				+ "\"dataNascimento\": \"01/01/2000\"}";
		
		mvc
		.perform(
				post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
		.andExpect(
				status().isCreated()
				)
		.andExpect(
				header().exists("Location")
				)
		.andExpect(
				content().json(jsonRequest)
				);
		
	}
	
	@Test
	void naoDeveriaCadastrarUsuarioSemNome() throws Exception {
		
		String jsonRequest = "{\"email\": \"usuario@email.com.br\", "
				+ "\"cpf\": \"08277450010\", "
				+ "\"dataNascimento\": \"01/01/2000\"}";
		
		mvc
		.perform(
				post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
		.andExpect(
				status().isBadRequest()
				);
		
	}
	
	@Test
	void naoDeveriaCadastrarUsuarioSemEmail() throws Exception {
		
		String jsonRequest = "{\"nome\": \"Usuario\", "
				+ "\"cpf\": \"08277450010\", "
				+ "\"dataNascimento\": \"01/01/2000\"}";
		
		mvc
		.perform(
				post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
		.andExpect(
				status().isBadRequest()
				);
		
	}
	
	@Test
	void naoDeveriaCadastrarUsuarioSemCpf() throws Exception {
		
		String jsonRequest = "{\"nome\": \"Usuario\", "
				+ "\"email\": \"usuario@email.com.br\", "
				+ "\"dataNascimento\": \"01/01/2000\"}";
		
		mvc
		.perform(
				post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
		.andExpect(
				status().isBadRequest()
				);
		
	}
	
	@Test
	void naoDeveriaCadastrarUsuarioSemDataNascimento() throws Exception {
		
		String jsonRequest = "{\"nome\": \"Usuario\", "
				+ "\"email\": \"usuario@email.com.br\", "
				+ "\"cpf\": \"08277450010\"}";
		
		mvc
		.perform(
				post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
		.andExpect(
				status().isBadRequest()
				);
		
	}
	
	@Test
	void naoDeveriaCadastrarUsuarioComEmailInvalido() throws Exception {
		
		String jsonRequest = "{\"nome\": \"Usuario\", "
				+ "\"email\": \"usuarioemail.com.br\", "
				+ "\"cpf\": \"08277450010\", "
				+ "\"dataNascimento\": \"01/01/2000\"}";
		
		mvc
		.perform(
				post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
		.andExpect(
				status().isBadRequest()
				);
		
	}
	
	@Test
	void naoDeveriaCadastrarUsuarioComCpfInvalido() throws Exception {
		
		String jsonRequest = "{\"nome\": \"Usuario\", "
				+ "\"email\": \"usuario@email.com.br\", "
				+ "\"cpf\": \"12345678900\", "
				+ "\"dataNascimento\": \"01/01/2000\"}";
		
		mvc
		.perform(
				post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
		.andExpect(
				status().isBadRequest()
				);
		
	}
	
	@Test
	void naoDeveriaCadastrarUsuarioComDataNascimentoNoFormatoInvalido() throws Exception {
		
		String jsonRequest = "{\"nome\": \"Usuario\", "
				+ "\"email\": \"usuario@email.com.br\", "
				+ "\"cpf\": \"08277450010\", "
				+ "\"dataNascimento\": \"2000-01-01\"}";
		
		mvc
		.perform(
				post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
		.andExpect(
				status().isBadRequest()
				);
		
	}
	
	@Test
	void deveriaCadastrarQuadrinhoComDadosValidos() throws Exception {
		
		Usuario usuario = new Usuario("Antonio","antonio@email.com.br","12345678900", LocalDate.of(2000, 1, 1));
		
		usuario = usuarioRepository.save(usuario);		
		
		String jsonRequest = "{\"comicId\": 1328}";
		
		String jsonResponse = "{\"comicId\":1328,\"titulo\":\"4 Vol. 1: Wolf at the Door (Trade Paperback)\","
				+ "\"isbn\":\"0-7851-1471-8\","
				+ "\"descricao\":\"Comics' first family face their most personal and unpredictable crisis yet in this edgy MARVEL KNIGHTS series. "
				+ "Writer Roberto Aguirre-Sacasa and rising-superstar artist Steve McNiven pit the Fantastic Four against a challenge the likes of "
				+ "which they've never faced, and to overcome it they'll need to call on the same humor, determination and family bonds that have "
				+ "delivered them from danger time and time again. This is the FF ... the 4 ... like you've never seen them before!\","
				+ "\"descontoAtivo\":false,\"precos\":[{\"tipo\":\"printPrice\",\"preco\":9.99}],"
				+ "\"autores\":[{\"uri\":\"http://gateway.marvel.com/v1/public/creators/7129\",\"nome\":\"Virtual Calligraphy\"},"
				+ "{\"uri\":\"http://gateway.marvel.com/v1/public/creators/8635\",\"nome\":\"Vc Randy Gentile\"},"
				+ "{\"uri\":\"http://gateway.marvel.com/v1/public/creators/774\",\"nome\":\"Morry Hollowell\"},"
				+ "{\"uri\":\"http://gateway.marvel.com/v1/public/creators/454\",\"nome\":\"Mark Morales\"},"
				+ "{\"uri\":\"http://gateway.marvel.com/v1/public/creators/4593\",\"nome\":\"Warren Simons\"}]}";
		
		mvc
		.perform(
				post("/usuarios/" + usuario.getId() + "/quadrinhos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
		.andExpect(
				status().isCreated()
				)
		.andExpect(
				header().exists("Location")
				)
		.andExpect(
				content().json(jsonResponse)
				);
		
	}
	
	@Test
	void naoDeveriaCadastrarQuadrinhoSemComicId() throws Exception {
		
		Usuario usuario = new Usuario("Antonio","antonio@email.com.br","12345678900", LocalDate.of(2000, 1, 1));
		
		usuario = usuarioRepository.save(usuario);		
		
		String jsonRequest = "{}";
		
		mvc
		.perform(
				post("/usuarios/" + usuario.getId() + "/quadrinhos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
		.andExpect(
				status().isBadRequest()
				);
		
	}

}
