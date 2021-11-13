package org.serratec.ecommerce.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    @ApiModelProperty(value = "Identificador único do pedido")
    private Long id;
    
    @Column(name = "data_pedido")
    @ApiModelProperty(value = "Data da efetuação do pedido", required = true)
    private LocalDateTime dataPedido;

    @ApiModelProperty(value = "Valor total dos itens do pedido", required = true)
    private Double valorTotal;

    @Column(name = "data_envio")
    @ApiModelProperty(value = "Data de envio do pedido", required = true)
    private LocalDateTime dataEnvio;

    @Column(name = "data_entrega")
    @ApiModelProperty(value = "Data da entrega do pedido ao cliente")
    private LocalDateTime dataEntrega;
    
    @ApiModelProperty(value = "Status do pedido", required = true)
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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        return Objects.equals(id, other.id);
    }
}
