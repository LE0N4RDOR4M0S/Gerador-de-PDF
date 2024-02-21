package com.example.demo.Domain;


import org.springframework.stereotype.Component;

@Component
public class Treino {
    String Inicio;
    String periodo;
    String prescricao;
    String Objetivo;
    String GrupoMuscular;
    String tipo;

    public Treino(String inicio, String periodo, String prescricao, String objetivo, String tipo,
            String grupoMuscular) {
        Inicio = inicio;
        this.periodo = periodo;
        this.prescricao = prescricao;
        Objetivo = objetivo;
        GrupoMuscular = grupoMuscular;
    }

    public Treino() {
        super();
    }

    public String getInicio() {
        return Inicio;
    }

    public void setInicio(String inicio) {
        Inicio = inicio;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public String getGrupoMuscular() {
        return GrupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        GrupoMuscular = grupoMuscular;
    }

    public String getObjetivo() {
        return Objetivo;
    }

    public void setObjetivo(String objetivo) {
        Objetivo = objetivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
}

