package beans;

import entities.Instituicao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author CristianoSilva
 */
@ManagedBean
@SessionScoped
public class InstituicaoBean {
    private Instituicao instituicao = new Instituicao();
    private Instituicao instituicaoSelecionada;
    private List<Instituicao> instituicoes;
    private List<Instituicao> listaFiltrada;
    
// Getters e Setters
    public List<Instituicao> getListaFiltrada() {
        return this.listaFiltrada;
    }
 
    public void setListaFiltrada(List<Instituicao> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public Instituicao getInstituicao() {
        return this.instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public void setInstituicaoSelecionado(Instituicao instituicao) {
        this.instituicaoSelecionada = instituicao;
    }
      
    public List<Instituicao> getInstituicoes(){
        this.instituicoes = this.instituicao.buscarTodos();
        return this.instituicoes;
    }
    
    public void setInstituicoes(List<Instituicao> lista){
        this.instituicoes = lista;
    }
    
// Ações
    public String detalhar(Long id){
        if(id != null)
            this.instituicaoSelecionada = this.instituicao.buscarPeloId(id);

        if (this.instituicaoSelecionada == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Instituição!",
                                   "Erro ao localizar Instituição!"));
            return "/pages/listar/listarInstituicoes";
        }
        else {
            this.instituicao = instituicaoSelecionada;
            return "/pages/detalhes/detalhesInstituicao?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            this.instituicaoSelecionada = this.instituicao.buscarPeloId(id);

        if (this.instituicaoSelecionada == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Instituição!",
                                   "Erro ao localizar Instituição!"));
            return "/pages/listar/listarInstituicoes";
        }
        else {
            this.instituicao = this.instituicaoSelecionada;
            return "/pages/editar/editarInstituicao?faces-redirect=true";
        }
    }  
    
    public void limpar(){
        this.instituicao = new Instituicao();
        this.instituicaoSelecionada = new Instituicao();
    }
    
    public String remover(Long id) {
        if(this.instituicao.remover(id))
            return "/pages/listar/listarInstituicoes?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Instituição!",
                                   "Erro ao excluir Instituição!"));
            return "/pages/listar/listarInstituicoes";
        }
    }
    
    public String salvar() {
        if(this.instituicao.salvar())
            return "/pages/listar/listarInstituicoes?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Instituição!",
                                   "Erro ao salvar Instituição!"));
            return "/pages/cadastrar/cadastrarInstituicoes";
        }
    }
}

