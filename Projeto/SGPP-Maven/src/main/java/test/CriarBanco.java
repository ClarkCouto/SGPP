
import dao.InstituicaoDAO;
import entities.Instituicao;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CristianoSilva
 */
public class CriarBanco {
    public static void main(String[] args) {
        try {
            
            System.out.println("" +
            "================================\n" +
            "================================\n" +
            "====== Cria Instituicao ========\n" +
            "================================\n" +
            "================================\n");
            Instituicao instituicao = new Instituicao();
            instituicao.setAtivo(Boolean.TRUE);
            instituicao.setNome("nome");
            instituicao.setDataCriacao(new Date());
            instituicao.setDataUltimaAlteracao(new Date());
            
            //Pesiste a Instituicao
            System.out.println("Inicia Mapeamento!");
            System.out.println("Persistir Instituicao!");
            instituicao.salvar();
            
            //Busca a Instituicao que foi cadastrada
            System.out.println("Encontrar Instituicao!");
            instituicao = new InstituicaoDAO().findById(1L);
            
            //Imprime a Instituicao que foi cadastrada
            System.out.println(instituicao);
            
            System.out.println("Finalizou Mapeamento!");
            
        } catch (RuntimeException e) {
            
            System.out.println("Exceção Projeto!");
            e.printStackTrace();
        }
    }
}
