package com.wprotheus.lista_tarefas.model;

import java.io.Serializable;


public class Tarefa implements Serializable {
    private int id;
    private String titulo;
    private String descricao;
    private String dataFim;
    private boolean status;

    public Tarefa() {
    }

    public Tarefa(int id, String titulo, String descricao, String dataFim) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataFim = dataFim;
        this.status = false;
    }

    public Tarefa(String titulo, String descricao, String dataFim) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataFim = dataFim;
        this.status = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tarefa{");
        sb.append("id=").append(id);
        sb.append(", titulo='").append(titulo).append('\'');
        sb.append(", descricao='").append(descricao).append('\'');
        sb.append(", dataFim='").append(dataFim).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}