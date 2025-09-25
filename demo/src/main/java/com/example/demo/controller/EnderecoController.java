package com.example.demo.controller;

import com.example.demo.entity.Endereco;
import com.example.demo.service.EnderecoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> listarEnderecos(){
        return enderecoService.findAllEndereco();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Long id){
        return enderecoService.findEnderecoById(id)
                .map(endereco -> new ResponseEntity<>(endereco, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}")
    public Endereco salvarEndereco(@PathVariable Long id, @RequestBody Endereco endereco){
        return enderecoService.save(id, endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Endereco> deletarEnderecoPorId(@PathVariable Long id){
        enderecoService.deleteEnderecoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @RequestBody Endereco novoEndereco){
        Endereco endereco = enderecoService.updateEnderecoByAlunoId(id, novoEndereco);
        return ResponseEntity.ok(endereco);
    }
}
