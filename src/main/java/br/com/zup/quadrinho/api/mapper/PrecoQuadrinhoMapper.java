package br.com.zup.quadrinho.api.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.zup.quadrinho.api.dto.ComicPriceDto;
import br.com.zup.quadrinho.api.dto.PrecoQuadrinhoDto;
import br.com.zup.quadrinho.api.model.PrecoQuadrinho;

@Component
public class PrecoQuadrinhoMapper {
	
	public PrecoQuadrinho toPrecoQuadrinho(ComicPriceDto comicPriceDto) {
		
		PrecoQuadrinho precoQuadrinho = new PrecoQuadrinho(
				comicPriceDto.getType(), 
				new BigDecimal(comicPriceDto.getPrice().toString()));
		
		return precoQuadrinho;
		
	}
	
	public PrecoQuadrinhoDto toPrecoQuadrinhoDto(PrecoQuadrinho precoQuadrinho) {
		
		PrecoQuadrinhoDto precoQuadrinhoDto = new PrecoQuadrinhoDto(
				precoQuadrinho.getTipo(), 
				precoQuadrinho.getPreco());
		
		return precoQuadrinhoDto;
		
	}

}
