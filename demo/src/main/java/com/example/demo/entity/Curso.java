package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private int cargaHoraria;
    private int qntdSemestre;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "centro_id")
    private Centro centro;

    @ManyToMany(mappedBy = "cursos")
    private List<Aluno> alunos;

    public Curso(long id, String nome, int cargaHoraria, int qntdSemestre, String descricao, Centro centro) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.qntdSemestre = qntdSemestre;
        this.descricao = descricao;
        this.centro = centro;
    }

    public Curso() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getQntdSemestre() {
        return qntdSemestre;
    }

    public void setQntdSemestre(int qntdSemestre) {
        this.qntdSemestre = qntdSemestre;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
