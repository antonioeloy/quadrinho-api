package br.com.zup.quadrinho.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.quadrinho.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Boolean existsByIdAndQuadrinhos_ComicId(Long usuarioId, Integer comicId);

}
