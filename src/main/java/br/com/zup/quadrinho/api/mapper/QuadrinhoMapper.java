package br.com.zup.quadrinho.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zup.quadrinho.api.dto.AutorDto;
import br.com.zup.quadrinho.api.dto.ComicDataWrapperDto;
import br.com.zup.quadrinho.api.dto.ComicDto;
import br.com.zup.quadrinho.api.dto.PrecoQuadrinhoDto;
import br.com.zup.quadrinho.api.dto.QuadrinhoDto;
import br.com.zup.quadrinho.api.model.IsbnData;
import br.com.zup.quadrinho.api.model.PrecoQuadrinho;
import br.com.zup.quadrinho.api.model.Quadrinho;

@Component
public class QuadrinhoMapper {
	
	@Autowired
	private PrecoQuadrinhoMapper precoQuadrinhoMapper;
	
	@Autowired
	private AutorMapper autorMapper;
	
	public Quadrinho toQuadrinho(ComicDataWrapperDto comicDataWrapperDto) {
		
		ComicDto comicDto = comicDataWrapperDto.getData().getResults().get(0);
		
		Quadrinho quadrinho = new Quadrinho(
				comicDto.getId(),
				comicDto.getTitle(), 
				new IsbnData(comicDto.getIsbn()),
				comicDto.getDescription());
		
		List<PrecoQuadrinho> precos = comicDto.getPrices().stream()
				.map(precoQuadrinhoMapper::toPrecoQuadrinho)
				.collect(Collectors.toList());
		
		precos.forEach(preco -> {
			preco.setQuadrinho(quadrinho);
			quadrinho.adicionaPreco(preco);
		});
				
		return quadrinho;
		
	}
	
	public QuadrinhoDto toQuadrinhoDto(Quadrinho quadrinho) {
		
		QuadrinhoDto quadrinhoDto = new QuadrinhoDto(
				quadrinho.getId(), 
				quadrinho.getComicId(), 
				quadrinho.getTitulo(), 
				quadrinho.getIsbnData().getIsbn(), 
				quadrinho.getDescricao(),
				quadrinho.descontoPrecoEstaAtivo());
		
		List<PrecoQuadrinhoDto> precos = quadrinho.getPrecos().stream()
				.map(precoQuadrinhoMapper::toPrecoQuadrinhoDto)
				.collect(Collectors.toList());
		
		precos.forEach(quadrinhoDto::adicionaPreco);
		
		List<AutorDto> autores = quadrinho.getAutores().stream()
				.map(autorMapper::toAutorDto)
				.collect(Collectors.toList());
		
		autores.forEach(quadrinhoDto::adicionaAutor);
		
		return quadrinhoDto;
		
	}

}
