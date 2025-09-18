package com.example.demo.service;

import com.example.demo.entity.Endereco;
import com.example.demo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> finAllEndereco() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findEnderecoById(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco updateEnderecoById(Long id, Endereco updateEndereco) {
        return enderecoRepository.findById(id)
                .map(e -> {
                    e.setLogradouro(updateEndereco.getLogradouro());
                    e.setNumero(updateEndereco.getNumero());
                    e.setComplemento(updateEndereco.getComplemento());
                    e.setCep(updateEndereco.getCep());

                    return enderecoRepository.save(e);
                })
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado!"));
    }

    public void deleteEnderecoById(Long id) {
        enderecoRepository.deleteById(id);
    }
}
