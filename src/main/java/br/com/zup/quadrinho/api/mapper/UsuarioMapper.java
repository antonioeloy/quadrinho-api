package br.com.zup.quadrinho.api.mapper;

import org.springframework.stereotype.Component;

import br.com.zup.quadrinho.api.dto.UsuarioDto;
import br.com.zup.quadrinho.api.dto.UsuarioFormDto;
import br.com.zup.quadrinho.api.model.Usuario;

@Component
public class UsuarioMapper {
	
	public Usuario toUsuario(UsuarioFormDto usuarioFormDto) {
		
		Usuario usuario = new Usuario(
				usuarioFormDto.getNome(), 
				usuarioFormDto.getEmail(), 
				usuarioFormDto.getCpf(), 
				usuarioFormDto.getDataNascimento());
		
		return usuario;
		
	}
	
	public UsuarioDto toUsuarioDto(Usuario usuario) {
		
		UsuarioDto usuarioDto = new UsuarioDto(
				usuario.getId(), 
				usuario.getNome(), 
				usuario.getEmail(), 
				usuario.getCpf(), 
				usuario.getDataNascimento());
		
		return usuarioDto;
		
	}

}
