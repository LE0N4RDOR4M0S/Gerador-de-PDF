package com.example.demo.Domain;

import java.util.List;

public class Formulario {
    Aluno aluno;
    Treino Treino;
    List<Exercicio> exercicio;
    
    public Formulario(Aluno aluno, com.example.demo.Domain.Treino treino, List<Exercicio> exercicio) {
        this.aluno = aluno;
        Treino = treino;
        this.exercicio = exercicio;
    }

    public Formulario() {
        
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Treino getTreino() {
        return Treino;
    }

    public void setTreino(Treino treino) {
        Treino = treino;
    }

    public List<Exercicio> getExercicio() {
        return exercicio;
    }

    public void setExercicio(List<Exercicio> exercicio) {
        this.exercicio = exercicio;
    }

    
}
