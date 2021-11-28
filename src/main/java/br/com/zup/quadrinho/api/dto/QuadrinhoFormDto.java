package br.com.zup.quadrinho.api.dto;

import javax.validation.constraints.NotNull;

public class QuadrinhoFormDto {

	@NotNull
	private Integer comicId;

	public QuadrinhoFormDto() {
	}

	public Integer getComicId() {
		return comicId;
	}

	public void setComicId(Integer comicId) {
		this.comicId = comicId;
	}

}
