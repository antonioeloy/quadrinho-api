package br.com.zup.quadrinho.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.quadrinho.api.model.Quadrinho;

public interface QuadrinhoRepository extends JpaRepository<Quadrinho, Long> {

	public Boolean existsByComicId(Integer comicId);
	
	public Boolean existsByIsbnData_Isbn(String isbn);

	public Quadrinho getByComicId(Integer comicId);
	
	public Page<Quadrinho> findAllByUsuarios_Id(Long usuarioId, Pageable paginacao);

}
