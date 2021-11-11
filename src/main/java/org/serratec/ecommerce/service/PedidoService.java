package org.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Pedido;
import org.serratec.ecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

        @Autowired
        private PedidoRepository pedidoRepository;

        public List<Pedido> obterTodos() {
            return pedidoRepository.findAll();
        }

        public Optional<Pedido> buscar(Long id) {
            return pedidoRepository.findById(id);
        }

        public Pedido criar(@Valid Pedido pedido) {
            return pedidoRepository.save(pedido);
        }

        public Pedido atualizar(Long id, @Valid Pedido pedido) {
            if(!pedidoRepository.existsById(id)) {
                return null;
            }
            pedido.setId(id);
            return pedidoRepository.save(pedido);
        }

        public Boolean deletar(Long id) {
            if(pedidoRepository.existsById(id)) {
                pedidoRepository.deleteById(id);;
                return true;
            }
            return false;
        }
}
