package org.serratec.ecommerce.dto;

import java.time.LocalDateTime;

import org.serratec.ecommerce.domain.Pedido;

public class PedidoDTO {
	private LocalDateTime dataPedido;
	private Double valorTotal;
	private LocalDateTime dataEnvio;
	private LocalDateTime dataEntrega;
	private String status;
	private ClienteDTO clienteDTO;
	
	public PedidoDTO() {	
	}

	public PedidoDTO(LocalDateTime dataPedido, Double valorTotal, LocalDateTime dataEnvio, LocalDateTime dataEntrega,
			String status, ClienteDTO clienteDTO) {
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
		this.dataEnvio = dataEnvio;
		this.dataEntrega = dataEntrega;
		this.status = status;
		this.clienteDTO = clienteDTO;
	}
	
	public PedidoDTO(Pedido pedido) {
		this.dataPedido = pedido.getDataPedido();
		this.valorTotal = pedido.getValorTotal();
		this.dataEnvio = pedido.getDataEnvio();
		this.dataEntrega = pedido.getDataEntrega();
		this.status = pedido.getStatus();
		this.clienteDTO = new ClienteDTO(pedido.getCliente());
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

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}
}
