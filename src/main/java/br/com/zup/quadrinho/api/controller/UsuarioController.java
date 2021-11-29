package br.com.zup.quadrinho.api.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.quadrinho.api.dto.QuadrinhoDto;
import br.com.zup.quadrinho.api.dto.QuadrinhoFormDto;
import br.com.zup.quadrinho.api.dto.UsuarioDto;
import br.com.zup.quadrinho.api.dto.UsuarioFormDto;
import br.com.zup.quadrinho.api.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ApiOperation("Cadastra um usuário")
	@PostMapping
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioFormDto usuarioFormDto,
			UriComponentsBuilder uriBuilder) {
		
		UsuarioDto usuarioDto = usuarioService.cadastrar(usuarioFormDto);
		
		URI uri = uriBuilder
				.path("/usuarios/{usuarioId}")
				.buildAndExpand(usuarioDto.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(usuarioDto);
		
	}
	
	@ApiOperation("Cadastra um quadrinho para um determinado usuário")
	@PostMapping("/{usuarioId}/quadrinhos")
	public ResponseEntity<QuadrinhoDto> cadastrarQuadrinho(@PathVariable @NotNull Long usuarioId, 
			@RequestBody @Valid QuadrinhoFormDto quadrinhoFormDto, 
			UriComponentsBuilder uriBuilder) {
		
		QuadrinhoDto quadrinhoDto = usuarioService.cadastrarQuadrinho(usuarioId, quadrinhoFormDto);
		
		URI uri = uriBuilder
				.path("/usuarios/{usuarioId}/quadrinhos/{quadrinhoId}")
				.buildAndExpand(usuarioId, quadrinhoDto.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(quadrinhoDto);
		
	}
	
	@ApiOperation("Consulta a lista de usuários cadastrados na aplicação")
	@GetMapping
	public Page<UsuarioDto> listar(@PageableDefault(size = 5) Pageable paginacao) {
		
		Page<UsuarioDto> usuarios = usuarioService.listar(paginacao);
		
		return usuarios;
		
	}
	
	@ApiOperation("Consulta a lista de quadrinhos de um determinado usuário")
	@GetMapping("/{usuarioId}/quadrinhos")
	public Page<QuadrinhoDto> listarQuadrinhos(@PathVariable @NotNull Long usuarioId,
			@PageableDefault(size = 5) Pageable paginacao) {
		
		Page<QuadrinhoDto> quadrinhos = usuarioService.listarQuadrinhos(usuarioId, paginacao);
		
		return quadrinhos;
		
	}
	
}
