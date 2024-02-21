package com.example.demo.Domain;

import org.springframework.stereotype.Component;

@Component
public class Aluno {
    String nome;
    String Data;
    Double Peso;
    String Altura;
    String Anamnese;
    String Obs;
    String objetivos;

    public Aluno(String nome, String data, Double peso, String altura, String anamnese, String obs, String objetivos) {
        this.nome = nome;
        Data = data;
        Peso = peso;
        Altura = altura;
        Anamnese = anamnese;
        Obs = obs;
        this.objetivos = objetivos;
    }

    public Aluno() {
        super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public Double getPeso() {
        return Peso;
    }

    public void setPeso(Double peso) {
        Peso = peso;
    }

    public String getAltura() {
        return Altura;
    }

    public void setAltura(String altura) {
        Altura = altura;
    }

    public String getAnamnese() {
        return Anamnese;
    }

    public void setAnamnese(String anamnese) {
        Anamnese = anamnese;
    }

    public String getObs() {
        return Obs;
    }

    public void setObs(String obs) {
        Obs = obs;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

}
