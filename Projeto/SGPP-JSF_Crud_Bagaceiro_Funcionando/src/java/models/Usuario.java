/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author mathe
 */
public class Usuario {

    private String nome;
    private String email;
    private String telefoneCelular;
    private String telefoneFixo;
    private String cpf;
    private String sexo;
    private LocalDate dataNascimento;
    private LocalDateTime dataCadastro;
    private Boolean ativo;
    private String senha;

    // Construtores
    public Usuario() {
    }
    public Usuario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }
    public Usuario(String nome, String email, String telefoneCelular, String telefoneFixo, String cpf, String sexo, LocalDate dataNascimento, LocalDateTime dataCadastro, Boolean ativo, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefoneCelular = telefoneCelular;
        this.telefoneFixo = telefoneFixo;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
        this.senha = senha;
    }
    
    

    // Getters & Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
