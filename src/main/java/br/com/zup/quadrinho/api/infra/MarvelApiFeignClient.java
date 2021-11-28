package br.com.zup.quadrinho.api.infra;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.zup.quadrinho.api.dto.ComicDataWrapperDto;

@FeignClient(name = "${feign.client.name}", url = "${feign.client.url}")
public interface MarvelApiFeignClient {
	
	String TS = "${api.marvel.ts}";
	String API_KEY = "${api.marvel.api_key}";
	String HASH = "${api.marvel.hash}";

	@GetMapping("/v1/public/comics/{comicId}?ts=" + TS + "&apikey=" + API_KEY + "&hash=" + HASH)
	ComicDataWrapperDto getComic(@PathVariable int comicId);
	
}
