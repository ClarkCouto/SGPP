/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.TipoDocumentoDAO;
import entities.TipoDocumento;

/**
 *
 * @author matheus
 */
public class Teste1 {
    
    public static void main(String[] args) {
        TipoDocumento tipo = new TipoDocumento();
        
        tipo.setNome("CPF");
        
        TipoDocumentoDAO dao = new TipoDocumentoDAO();
        
        dao.save(tipo);
    }
    
    
}
