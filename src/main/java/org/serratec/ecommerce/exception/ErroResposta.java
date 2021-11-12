package org.serratec.ecommerce.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErroResposta {
	private int status;
	private String titulo;
	private LocalDateTime datahora;
	private List<String> erros;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public LocalDateTime getDatahora() {
		return datahora;
	}
	public void setDatahora(LocalDateTime datahora) {
		this.datahora = datahora;
	}
		
	public List<String> getErros() {
		return erros;
	}
	public void setErros(List<String> erros) {
		this.erros = erros;
	}
	public ErroResposta(int status, String titulo, LocalDateTime datahora, List<String> erros) {
		this.status = status;
		this.titulo = titulo;
		this.datahora = datahora;
		this.erros = erros;
	}
}
