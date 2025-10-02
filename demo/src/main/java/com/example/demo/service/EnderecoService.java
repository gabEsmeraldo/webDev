package com.example.demo.service;

import com.example.demo.entity.Aluno;
import com.example.demo.entity.Endereco;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Endereco> findAllEndereco() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findEnderecoById(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco save(Long alunoId, Endereco endereco) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));

        endereco.setAluno(aluno);

        return enderecoRepository.save(endereco);
    }

    public Endereco updateEnderecoByAlunoId(Long alunoId, Endereco updateEndereco) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));

        Endereco enderecoAtual = enderecoRepository.findAlunoById(alunoId)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado para o Aluno Id " + alunoId));

        enderecoAtual.setLogradouro(updateEndereco.getLogradouro());
        enderecoAtual.setComplemento(updateEndereco.getComplemento());
        enderecoAtual.setNumero(updateEndereco.getNumero());
        enderecoAtual.setCep(updateEndereco.getCep());

        enderecoAtual.setAluno(aluno);

        return enderecoRepository.save(enderecoAtual);
    }

    public void deleteEnderecoById(Long id) {
        enderecoRepository.deleteById(id);
    }
}
