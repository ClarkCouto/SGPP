/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDateTime;

/**
 *
 * @author mathe
 */
public class Edital {   
    private Long idEdital;
    private String titulo;
    private String numero;
    private String origem;
    private String pdf;
    private Boolean ativo;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataUltimaAlteracao;
    private Usuario alteradoPor;
    
// Construtores

    public Edital() {
    }
    public Edital(Long idEdital, String titulo, String numero, String origem, LocalDateTime dataCadastro) {
        this.idEdital = idEdital;
        this.titulo = titulo;
        this.numero = numero;
        this.origem = origem;
        this.dataCadastro = dataCadastro;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }
    
    
// Getters & Setters

    public Long getIdEdital() {
        return idEdital;
    }

    public void setIdEdital(Long idEdital) {
        this.idEdital = idEdital;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public Usuario getAlteradoPor() {
        return alteradoPor;
    }

    public void setAlteradoPor(Usuario alteradoPor) {
        this.alteradoPor = alteradoPor;
    }
    
// Outros métodos

    @Override
    public String toString() {
        return "Edital{" + "idEdital=" + idEdital + ", titulo=" + titulo + ", numero=" + numero + ", origem=" + origem + ", pdf=" + pdf + ", ativo=" + ativo + ", dataCadastro=" + dataCadastro + ", dataUltimaAlteracao=" + dataUltimaAlteracao + ", alteradoPor=" + alteradoPor + '}';
    }
    
}
