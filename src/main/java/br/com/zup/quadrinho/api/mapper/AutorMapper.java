package br.com.zup.quadrinho.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.zup.quadrinho.api.dto.AutorDto;
import br.com.zup.quadrinho.api.dto.ComicDataWrapperDto;
import br.com.zup.quadrinho.api.dto.CreatorSummaryDto;
import br.com.zup.quadrinho.api.model.Autor;

@Component
public class AutorMapper {
	
	public Autor toAutor(CreatorSummaryDto creatorSummaryDto) {
		
		Autor autor = new Autor(
				creatorSummaryDto.getResourceURI(), 
				creatorSummaryDto.getName());
		
		return autor;
		
	}
	
	public List<Autor> toListAutor(ComicDataWrapperDto comicDataWrapperDto) {
		
		List<CreatorSummaryDto> creators = comicDataWrapperDto.getData().getResults().get(0).getCreators().getItems();
		
		List<Autor> autores = creators.stream()
				.map(this::toAutor)
				.collect(Collectors.toList());
		
		return autores;
		
	}
	
	public AutorDto toAutorDto(Autor autor) {
		
		AutorDto autorDto = new AutorDto(
				autor.getUri(), 
				autor.getNome());
		
		return autorDto;
		
	}

}
