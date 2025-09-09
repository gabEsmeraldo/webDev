package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<AlunoDTO> findAllAlunos(){
        return alunoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<AlunoDTO> findAlunoById(Long id) {
        return alunoRepository.findById(id)
                .map(this::toDTO);
    }

    public AlunoDTO saveAluno(Aluno aluno) {
        Aluno alunoSaved = alunoRepository.save(aluno);
        return toDTO(alunoSaved);
    }

    public void deleteAluno(Long id) {
        alunoRepository.deleteById(id);
    }

    public AlunoDTO updateAluno(Long id, Aluno updateAluno) {
        return alunoRepository.findById(id)
                .map(aluno -> {
                    aluno.setNome(updateAluno.getNome());
                    aluno.setEmail(updateAluno.getEmail());
                    aluno.setDataNasc(updateAluno.getDataNasc());
                    aluno.setSenha(updateAluno.getSenha());

                    Aluno alunoSaved = alunoRepository.save(aluno);
                    return toDTO(alunoSaved);
                }).orElseThrow(() -> new RuntimeException("Aluno não existe!"));
    }


    //Parte do DTO

    public Optional<AlunoDTO> getAlunoById(Long id){
        Aluno aluno = alunoRepository.findById(id).orElse(null);
        if(aluno != null){
            return convertToDTO(aluno);
        }
        return Optional.empty();
    }

    public void saveAluno(AlunoDTO alunoDTO){
        Aluno aluno = convertToEntity(alunoDTO);
        alunoRepository.save(aluno);
    }

    private Aluno convertToEntity(AlunoDTO alunoDTO){
        return new Aluno(alunoDTO.getId(), alunoDTO.getNome(), alunoDTO.getEmail());
    }
    // Mapper
    private AlunoDTO toDTO(Aluno aluno){
        return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail());
    }
}
