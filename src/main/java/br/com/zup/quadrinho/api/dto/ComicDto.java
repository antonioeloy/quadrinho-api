package br.com.zup.quadrinho.api.dto;

import java.util.List;

public class ComicDto {

	private Integer id;
	private String title;
	private String description;
	private String isbn;
	private List<ComicPriceDto> prices;
	private CreatorListDto creators;

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getIsbn() {
		return isbn;
	}

	public List<ComicPriceDto> getPrices() {
		return prices;
	}

	public CreatorListDto getCreators() {
		return creators;
	}

}
