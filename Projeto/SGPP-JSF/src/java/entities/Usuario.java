/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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
    
// Outros métodos
    
    public Boolean checkSenha(String s) {
        return this.senha.equals(s);
    }
    
    public Boolean changeSenha(String newPass, String oldPass) {
        if (Objects.equals(this.senha, oldPass)) {
           this.senha = newPass; 
           return true;
        }        
        return false;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", email=" + email + ", telefoneCelular=" + telefoneCelular + ", telefoneFixo=" + telefoneFixo + ", cpf=" + cpf + ", sexo=" + sexo + ", dataNascimento=" + dataNascimento + ", dataCadastro=" + dataCadastro + ", ativo=" + ativo + ", senha=" + senha + '}';
    }    
    
}
