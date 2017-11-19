/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author matheus
 */
public enum PaginasEnum {
    
    EDITAIS(1),
    PROJETOS(2),
    ALUNOS(3),
    COORDENADORES(4),
    RELATORIOS(5),
    DECLARACOES(6);
    
    private final int value;

    private PaginasEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    
    
    
}
