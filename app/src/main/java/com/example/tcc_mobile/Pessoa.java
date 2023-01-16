package com.example.tcc_mobile;

import java.io.Serializable;

public class Pessoa implements Serializable {

    //criação de três atributos

    private String nome;
    private int senha;
    private String email;

    //pressiona alt+Insert
    // cria o construtor vazio
    public Pessoa() {
    }

    //criar os getters e setters
    //alt+insert e seleciona

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int idade) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String altura) {
        this.email = email;
    }
}
