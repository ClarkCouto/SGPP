/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CoordenadorDAO;
import dao.SetorDePesquisaDAO;
import dao.UsuarioDAO;
import entities.Coordenador;
import entities.SetorDePesquisa;
import entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author CristianoSilva
 */
@ManagedBean
@RequestScoped
public class UsuarioBean implements Serializable {    
    private Usuario usuario = new Usuario();
    private String tipoUsuario;
    private static List<String> tiposUsuario;
    
    // Getters e Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    static{
        tiposUsuario = new ArrayList<>();
        tiposUsuario.add("Cagppi");
        tiposUsuario.add("Coordenador");
        tiposUsuario.add("Setor de Pesquisa");
    }

    public List<String> getTiposUsuario() {
        return tiposUsuario;
    }
    
    // Ações
    public String cadastrar() {
        usuario.setAtivo(Boolean.TRUE);
        usuario.setDataCriacao(new Date());
        usuario.setDataNascimento(new Date());
        usuario.setDataUltimaAlteracao(new Date());
        usuario.setUltimoAcesso(new Date());
        usuario.setSenha("1234");
        
        try{
            switch(getTipoUsuario()){
                case "Cagppi":
//                    Cagppi cag = new Cagppi();
//                    cag.setAtivo(Boolean.TRUE);
//                    cag.setCpf(usuario.getCpf());
//                    cag.setDataCriacao(new Date());
//                    cag.setDataNascimento(new Date());
//                    cag.setDataUltimaAlteracao(new Date());
//                    cag.setEmail(usuario.getEmail());
//                    cag.setNome(usuario.getNome());
//                    cag.setSenha("1234");
//                    cag.setSexo(usuario.getSexo());
//                    cag.setTelefoneCelular(usuario.getTelefoneCelular());
//                    cag.setTelefoneFixo(usuario.getTelefoneFixo());
//                    cag.setUltimoAcesso(new Date());
//                    new CagppiDAO().save(cag);
                    new UsuarioDAO().save(usuario);
                    break;
                case "Coordenador":
                    new CoordenadorDAO().save((Coordenador) usuario);
                    break;
                case "SetorDePesquisa":
                    new SetorDePesquisaDAO().save((SetorDePesquisa) usuario);
                    break;
            }
            return "login";
        }
        catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar usuário!",
                                   "Erro ao cadastrar usuário!"));
            return "signup";
        }
    }
    
    public String logar() {
//        Usuario user = usuario.encontrarPeloCpf(usuario.getCpf().replaceAll("\\D+",""));
        Usuario user = new UsuarioDAO().findByCpf(usuario.getCpf().replaceAll("\\D+",""));
        if (user == null || !user.checkSenha(usuario.getSenha())) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                                   "Erro no Login!"));
//            return "login?wrong-credentials=true";
            return "/pages/listar/listarEditais?faces-redirect=true";
        }       
        return "/pages/listar/listarEditais?faces-redirect=true";
    }
    
    public void limpar() {
        setUsuario(new Usuario());
    }
}
