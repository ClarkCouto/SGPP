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
import entities.Usuario.TipoUsuario;
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
    public static final String AUTH_KEY = "app.user.name"; 
    private Usuario usuario = new Usuario(); 
    private Usuario usuarioLogado;
    private List<Usuario> usuarios;
    private List<Usuario> listaFiltrada;
    private String tipoUsuario;
    private static List<String> tiposUsuario;
    private HttpSession session;
    
    // Getters e Setters
    public List<Usuario> getListaFiltrada() {
        return this.listaFiltrada;
    }
 
    public void setListaFiltrada(List<Usuario> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<Usuario> getUsuarios() {
        this.usuarios = this.usuario.buscarTodos();
        return this.usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
   
    public Usuario getUsuarioLogado() {
        if(this.usuarioLogado == null){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            this.session = (HttpSession) facesContext.getExternalContext().getSession(false);
            this.usuarioLogado = (Usuario) this.session.getAttribute("usuarioLogado");
        }
        if(this.usuarioLogado == null)
            encerraSessao();
        return this.usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    public String getTipoUsuario() {
        return this.tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    static{
        tiposUsuario = new ArrayList<>();
        tiposUsuario.add("Cagppi");
        tiposUsuario.add("SetorDePesquisa");
//        tiposUsuario = TipoUsuario.values();
    }

    public List<String> getTiposUsuario() {
        return tiposUsuario;
    }
    
    // Ações
    public String cadastrar() {
        String tipo = "";
        boolean inserido = false;
        try{
            switch(getTipoUsuario()){
                case "Cagppi":
                    tipo = TipoUsuario.CAGPPI.toString();
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
                    cag.setTipo(TipoUsuario.CAGPPI);
                    cag.setUltimoAcesso(new Date());
                    inserido = cag.salvar();
                    break;
                default:
                    tipo = TipoUsuario.SetorDePesquisa.toString();
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
                    setor.setTipo(TipoUsuario.SetorDePesquisa);
                    setor.setUltimoAcesso(new Date());
                    inserido = setor.salvar();
                    break;
            }
            if(inserido)
                return "login";
            else{
                FacesContext.getCurrentInstance().addMessage(null,
                           new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar " + tipo + "!",
                                       "Erro ao cadastrar " + tipo + "!"));
                return "signup";
            }
        }
        catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar " + tipo + "!",
                                   "Erro ao cadastrar " + tipo + "!"));
            return "signup";
        }
    }
    
    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_KEY) != null;
    }
    
    public String logar() {
        Usuario user = this.usuario.buscarPeloCpf(this.usuario.getCpf());
        if (user == null || !user.checkSenha(this.usuario.getSenha())) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                                   "Erro no Login!"));
            return "login";
        }           
        setUsuarioLogado(user);
        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(false);
        session.setAttribute("usuarioLogado", this.usuarioLogado);
        ctx.getExternalContext().getSessionMap().put(AUTH_KEY, this.usuarioLogado.getNome());
        return "/pages/listar/listarUsuarios?faces-redirect=true";
    }
    
    public void encerraSessao() {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            session = (HttpSession) ctx.getExternalContext().getSession(false);
            session.setAttribute("usuarioLogado", null);
            ctx.getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/faces/login.xhtml");
            ctx.getExternalContext().getSessionMap().remove(AUTH_KEY);
            session.invalidate();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro ao efetuar loggout!",
                                   "Erro no Loggout!"));
        }
    }
    
    public void limpar() {
        setUsuario(new Usuario());
        setUsuarioLogado(null);
    }
}
