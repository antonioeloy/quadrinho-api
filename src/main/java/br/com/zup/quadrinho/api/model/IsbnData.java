package br.com.zup.quadrinho.api.model;

import java.time.DayOfWeek;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class IsbnData {

	@NotBlank
	private String isbn;

	public IsbnData() {
	}

	public IsbnData(String isbn) {
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getNumeroDigitos() {
		return this.isbn.length();
	}
	
	public Character getDigitoFinal() {
		return this.isbn.charAt(this.getNumeroDigitos() - 1);
	}
	
	public DayOfWeek getDiaSemanaDescontoPreco() {
		
		DayOfWeek diaSemanaDescontoPreco;
		
		if (this.getDigitoFinal().equals('0') || this.getDigitoFinal().equals('1')) {
			diaSemanaDescontoPreco = DayOfWeek.MONDAY;
		}	
		else if (this.getDigitoFinal().equals('2') || this.getDigitoFinal().equals('3')) {
			diaSemanaDescontoPreco = DayOfWeek.TUESDAY;
		}
		else if (this.getDigitoFinal().equals('4') || this.getDigitoFinal().equals('5')) {
			diaSemanaDescontoPreco = DayOfWeek.WEDNESDAY;
		}
		else if (this.getDigitoFinal().equals('6') || this.getDigitoFinal().equals('7')) {
			diaSemanaDescontoPreco = DayOfWeek.THURSDAY;
		}
		else {
			diaSemanaDescontoPreco = DayOfWeek.FRIDAY;
		}
		
		return diaSemanaDescontoPreco;
		
	}

}
