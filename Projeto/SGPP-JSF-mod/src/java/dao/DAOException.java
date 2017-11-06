/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author CristianoSilva
 */
public class DAOException extends RuntimeException{
    private String mensagem;

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "DAOException{" + "mensagem=" + mensagem + '}';
    }
}
