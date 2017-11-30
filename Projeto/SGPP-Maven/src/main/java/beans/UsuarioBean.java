/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Cagppi;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author CristianoSilva
 */
@ManagedBean
@RequestScoped
public class UsuarioBean implements Serializable {    
    private Usuario usuario = new Usuario(); 
    private Usuario usuarioLogado;
    private List<Usuario> usuarios;
    private List<Usuario> listaFiltrada;
    private String tipoUsuario;
    private static List<String> tiposUsuario;
    private HttpSession session;
    
    // Getters e Setters
    public List<Usuario> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<Usuario> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<Usuario> getUsuarios() {
        this.usuarios = (List<Usuario>)(Usuario) this.usuario.buscarTodos();
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
   
    public Usuario getUsuarioLogado() {
        if(this.usuarioLogado == null){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            session = (HttpSession) facesContext.getExternalContext().getSession(false);
            this.usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        }
        if(this.usuarioLogado == null)
            encerraSessao();
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
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
        String tipo = "";
        try{
            switch(getTipoUsuario()){
                case "Cagppi":
                    tipo = "Cagppi";
                    Cagppi cag = new Cagppi();
                    cag.setAtivo(Boolean.TRUE);
                    cag.setCpf(usuario.getCpf());
                    cag.setDataCriacao(new Date());
                    cag.setDataNascimento(new Date());
                    cag.setDataUltimaAlteracao(new Date());
                    cag.setEmail(usuario.getEmail());
                    cag.setNome(usuario.getNome());
                    cag.setSenha("1234");
                    cag.setSexo(usuario.getSexo());
                    cag.setTelefoneCelular(usuario.getTelefoneCelular());
                    cag.setTelefoneFixo(usuario.getTelefoneFixo());
                    cag.setTipo(tipo);
                    cag.setUltimoAcesso(new Date());
                    cag.salvar();
                    break;
                case "Coordenador":
                    tipo = "Coordenador";
                    Coordenador coordenador = new Coordenador();
                    coordenador.setAtivo(Boolean.TRUE);
                    coordenador.setCpf(usuario.getCpf());
                    coordenador.setDataCriacao(new Date());
                    coordenador.setDataNascimento(new Date());
                    coordenador.setDataUltimaAlteracao(new Date());
                    coordenador.setEmail(usuario.getEmail());
                    //coordenador.setGruposDePesquisa();
                    //coordenador.setInstituicao();
                    coordenador.setNome(usuario.getNome());
                    coordenador.setSenha("1234");
                    coordenador.setSexo(usuario.getSexo());
                    coordenador.setTelefoneCelular(usuario.getTelefoneCelular());
                    coordenador.setTelefoneFixo(usuario.getTelefoneFixo());
                    coordenador.setTipo(tipo);
                    coordenador.setUltimoAcesso(new Date());
                    coordenador.salvar();
                    break;
                case "Setor De Pesquisa":
                    tipo = "Setor De Pesquisa";
                    SetorDePesquisa setor = new SetorDePesquisa();
                    setor.setAtivo(Boolean.TRUE);
                    setor.setCpf(usuario.getCpf());
                    setor.setDataCriacao(new Date());
                    setor.setDataNascimento(new Date());
                    setor.setDataUltimaAlteracao(new Date());
                    setor.setEmail(usuario.getEmail());
                    setor.setNome(usuario.getNome());
                    setor.setSenha("1234");
                    setor.setSexo(usuario.getSexo());
                    setor.setTelefoneCelular(usuario.getTelefoneCelular());
                    setor.setTelefoneFixo(usuario.getTelefoneFixo());
                    setor.setTipo(tipo);
                    setor.setUltimoAcesso(new Date());
                    setor.salvar();
                    break;
            }
            return "login";
        }
        catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar " + tipo + "!",
                                   "Erro ao cadastrar " + tipo + "!"));
            return "signup";
        }
    }
    
    public String logar() {
        Usuario user = usuario.buscarPeloCpf(usuario.getCpf());
        if (user == null || !user.checkSenha(usuario.getSenha())) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                                   "Erro no Login!"));
            return "login";
            //return "/pages/listar/listarEditais?faces-redirect=true";
        }           
        setUsuarioLogado(user);
        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(false);
        session.setAttribute("usuarioLogado", usuarioLogado);
        return "/pages/listar/listarEditais?faces-redirect=true";
    }
    
    public void encerraSessao() {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            session = (HttpSession) ctx.getExternalContext().getSession(false);
            session.setAttribute("usuarioLogado", null);
            ctx.getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/faces/login.xhtml");
            session.invalidate();
        } catch (Exception e) {
        }
    }
    
    public void limpar() {
        setUsuario(new Usuario());
        setUsuarioLogado(null);
    }
}
