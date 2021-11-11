package org.serratec.ecommerce.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Long id;
	
	@Column(name = "data_pedido")
	private LocalDateTime dataPedido;

	private Double valorTotal;

	@Column(name = "data_envio")
	private LocalDateTime dataEnvio;

	@Column(name = "data_entrega")
	private LocalDateTime dataEntrega;
	
	private String status;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	public Pedido() {
	}

	public Pedido(Long id, LocalDateTime dataPedido, Double valorTotal, LocalDateTime dataEnvio,
			LocalDateTime dataEntrega, String status, Cliente cliente) {
		this.id = id;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
		this.dataEnvio = dataEnvio;
		this.dataEntrega = dataEntrega;
		this.status = status;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDateTime getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public LocalDateTime getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}
}
