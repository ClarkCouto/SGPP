/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author CristianoSilva
 */
public class Projeto implements Serializable{
    private String aipct;
    private String ano;
    private String dataInicio;
    private String dataFim;
    private String edital;
    private String titulo;

    public Projeto() {
    }

    public Projeto(String aipct, String ano, String ativo, String dataInicio, String dataFim, String edital, String titulo) {
        this.aipct = aipct;
        this.ano = ano;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.edital = edital;
        this.titulo = titulo;
    }

    public String getAipct() {
        return aipct;
    }

    public void setAipct(String aipct) {
        this.aipct = aipct;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getEdital() {
        return edital;
    }

    public void setEdital(String edital) {
        this.edital = edital;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LinkedList<Projeto> listar() {
        LinkedList<Projeto> projetos = new LinkedList<>();
        projetos.add(new Projeto("false", "2017", "true", "Início", "Fim" , "Edital 1", "Projeto 1"));
        projetos.add(new Projeto("false", "2015", "false", "Início", "Fim", "Edital 1", "Projeto 2"));
        return projetos;
    }
}
