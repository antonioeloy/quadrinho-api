package br.com.zup.quadrinho.api.infra;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.zup.quadrinho.api.dto.StatusCode400Dto;
import br.com.zup.quadrinho.api.dto.StatusCode500Dto;
import br.com.zup.quadrinho.api.dto.StatusCodeDto;
import br.com.zup.quadrinho.api.exception.PreenchimentoDadosException;

@RestControllerAdvice
public class TratamentoExcecoes {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<StatusCode400Dto> tratarConstraintViolationException(ConstraintViolationException ex) {	
		
		return ex
				.getConstraintViolations()
				.stream()
				.map(violation -> new StatusCode400Dto(violation.getPropertyPath().toString(), violation.getMessage()))
				.collect(Collectors.toList());
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<StatusCode400Dto> tratarMethodArgumentNotValidException(MethodArgumentNotValidException ex) {	
		
		return ex
				.getFieldErrors()
				.stream()
				.map(erro -> new StatusCode400Dto(erro.getField(), erro.getDefaultMessage()))
				.collect(Collectors.toList());
		
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public StatusCode400Dto tratarHttpMessageNotReadableException(HttpMessageNotReadableException ex1) {
		
		StatusCode400Dto statusCode400Dto = new StatusCode400Dto();
		
		if (ex1.getCause() instanceof InvalidFormatException && ex1.getRootCause() instanceof DateTimeParseException) {
			InvalidFormatException ex2 = (InvalidFormatException) ex1.getCause();
			statusCode400Dto.setCampo(ex2.getPath().get(0).getFieldName());
			statusCode400Dto.setMensagem("Deve estar no formato dd/mm/aaaa");
		}
		
		return statusCode400Dto;
		
	}
	
	@ExceptionHandler(PreenchimentoDadosException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public StatusCode400Dto tratarDadoEmUsoException(PreenchimentoDadosException ex) {
		
		return new StatusCode400Dto(ex.getCampo(), ex.getMessage());
		
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public StatusCodeDto tratarEntityNotFoundException(EntityNotFoundException ex) {
		
		return new StatusCodeDto(ex.getMessage());
		
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public StatusCode500Dto tratarException(Exception ex, HttpServletRequest req) {
		
		return new StatusCode500Dto(
				LocalDateTime.now(), 
				ex.getClass().toString(), 
				ex.getMessage(), 
				req.getRequestURI());
		
	}
	
}
