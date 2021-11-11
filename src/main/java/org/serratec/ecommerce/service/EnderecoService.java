package org.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Endereco;
import org.serratec.ecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> obterTodos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findById(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco criar(@Valid Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Long id, @Valid Endereco endereco) {
        if(!enderecoRepository.existsById(id)) {
            return null;
        }
        endereco.setId(id);
        return enderecoRepository.save(endereco);
    }

    public Boolean deletar(Long id) {
        if(enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);;
            return true;
        }
        return false;
    }
}
