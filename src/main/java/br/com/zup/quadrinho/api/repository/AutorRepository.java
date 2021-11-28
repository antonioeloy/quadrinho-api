package br.com.zup.quadrinho.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.quadrinho.api.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
	
	public Boolean existsByUri(String uri);
	
	public Autor getByUri(String uri);

}
