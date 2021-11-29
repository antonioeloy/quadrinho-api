package br.com.zup.quadrinho.api.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.quadrinho.api.dto.ComicDataWrapperDto;
import br.com.zup.quadrinho.api.dto.QuadrinhoDto;
import br.com.zup.quadrinho.api.dto.QuadrinhoFormDto;
import br.com.zup.quadrinho.api.dto.UsuarioDto;
import br.com.zup.quadrinho.api.dto.UsuarioFormDto;
import br.com.zup.quadrinho.api.exception.UsuarioJaPossuiQuadrinhoException;
import br.com.zup.quadrinho.api.infra.MarvelApiFeignClient;
import br.com.zup.quadrinho.api.mapper.AutorMapper;
import br.com.zup.quadrinho.api.mapper.QuadrinhoMapper;
import br.com.zup.quadrinho.api.mapper.UsuarioMapper;
import br.com.zup.quadrinho.api.model.Autor;
import br.com.zup.quadrinho.api.model.Quadrinho;
import br.com.zup.quadrinho.api.model.Usuario;
import br.com.zup.quadrinho.api.repository.AutorRepository;
import br.com.zup.quadrinho.api.repository.QuadrinhoRepository;
import br.com.zup.quadrinho.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private QuadrinhoRepository quadrinhoRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private MarvelApiFeignClient marvelApiFeignClient;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Autowired
	private QuadrinhoMapper quadrinhoMapper;
	
	@Autowired
	private AutorMapper autorMapper;
	
	@Transactional
	public UsuarioDto cadastrar(UsuarioFormDto usuarioFormDto) {
		
		Usuario usuario = usuarioMapper.toUsuario(usuarioFormDto);
		
		usuarioRepository.save(usuario);
		
		return usuarioMapper.toUsuarioDto(usuario);
		
	}
	
	@Transactional
	public QuadrinhoDto cadastrarQuadrinho(Long usuarioId, QuadrinhoFormDto quadrinhoFormDto) {
		
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
		
		Boolean usuarioJaPossuiQuadrinho = usuarioRepository.existsByIdAndQuadrinhos_ComicId(usuarioId, quadrinhoFormDto.getComicId());
		
		if (usuarioJaPossuiQuadrinho) {
			throw new UsuarioJaPossuiQuadrinhoException("comicId", "O usuário já possui o quadrinho com comicId=" + quadrinhoFormDto.getComicId());
		}
		
		Boolean quadrinhoJaCadastrado = quadrinhoRepository.existsByComicId(quadrinhoFormDto.getComicId());
		
		Quadrinho quadrinho;
		
		if (quadrinhoJaCadastrado) {	
			
			quadrinho = quadrinhoRepository.getByComicId(quadrinhoFormDto.getComicId());
			
		} else {
			
			ComicDataWrapperDto comicDataWrapperDto = marvelApiFeignClient.getComic(quadrinhoFormDto.getComicId());
			
			quadrinho = quadrinhoMapper.toQuadrinho(comicDataWrapperDto);
			
			List<Autor> autores = autorMapper.toListAutor(comicDataWrapperDto);
			
			for(Autor a : autores) {
				
				Boolean autorJaCadastrado = autorRepository.existsByUri(a.getUri());
				
				Autor autor;
				
				if (autorJaCadastrado) {
					autor = autorRepository.getByUri(a.getUri());
				} else {
					autor = autorRepository.save(a);
				}
				
				quadrinho.adicionaAutor(autor);
				
			}
			
			quadrinho = quadrinhoRepository.save(quadrinho);
			
		}
		
		usuario.adicionaQuadrinho(quadrinho);
		
		usuarioRepository.save(usuario);
		
		return quadrinhoMapper.toQuadrinhoDto(quadrinho);
			
	}
	
	public Page<UsuarioDto> listar(Pageable paginacao) {
		
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		
		return usuarios.map(usuarioMapper::toUsuarioDto);
		
	}

	public Page<QuadrinhoDto> listarQuadrinhos(Long usuarioId, Pageable paginacao) {
		
		Page<Quadrinho> quadrinhos = quadrinhoRepository.findAllByUsuarios_Id(usuarioId, paginacao);
		
		return quadrinhos.map(quadrinhoMapper::toQuadrinhoDto);
		
	}

}
