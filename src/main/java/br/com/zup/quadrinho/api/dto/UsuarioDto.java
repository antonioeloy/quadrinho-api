package br.com.zup.quadrinho.api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UsuarioDto {

	private Long id;
	private String nome;
	private String email;
	private String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	public UsuarioDto(Long id, String nome, String email, String cpf, LocalDate dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

}
