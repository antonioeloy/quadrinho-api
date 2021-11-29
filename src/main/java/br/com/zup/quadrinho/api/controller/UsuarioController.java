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

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
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
	
	@GetMapping
	public Page<UsuarioDto> listar(@PageableDefault(size = 5) Pageable paginacao) {
		
		Page<UsuarioDto> usuarios = usuarioService.listar(paginacao);
		
		return usuarios;
		
	}
	
	@GetMapping("/{usuarioId}/quadrinhos")
	public Page<QuadrinhoDto> listarQuadrinhos(@PathVariable @NotNull Long usuarioId,
			@PageableDefault(size = 5) Pageable paginacao) {
		
		Page<QuadrinhoDto> quadrinhos = usuarioService.listarQuadrinhos(usuarioId, paginacao);
		
		return quadrinhos;
		
	}
	
}
