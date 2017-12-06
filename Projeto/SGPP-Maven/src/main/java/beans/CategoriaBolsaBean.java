package beans;

import entities.CategoriaBolsa;
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
public class CategoriaBolsaBean {
    private CategoriaBolsa categoriaBolsa = new CategoriaBolsa();
    private CategoriaBolsa categoriaBolsaSelecionada;
    private List<CategoriaBolsa> categorias;
    private List<CategoriaBolsa> listaFiltrada;
    
// Getters e Setters
    public List<CategoriaBolsa> getListaFiltrada() {
        return this.listaFiltrada;
    }
 
    public void setListaFiltrada(List<CategoriaBolsa> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public CategoriaBolsa getCategoriaBolsa() {
        return this.categoriaBolsa;
    }

    public void setCategoriaBolsa(CategoriaBolsa categoriaBolsa) {
        this.categoriaBolsa = categoriaBolsa;
    }

    public void setCategoriaBolsaSelecionado(CategoriaBolsa categoriaBolsa) {
        this.categoriaBolsaSelecionada = categoriaBolsa;
    }
      
    public List<CategoriaBolsa> getCategorias(){
        this.categorias = this.categoriaBolsa.buscarTodos();
        return this.categorias;
    }
    
    public void setCategorias(List<CategoriaBolsa> lista){
        this.categorias = lista;
    }
    
// Ações
    public String detalhar(Long id){
        if(id != null)
            this.categoriaBolsaSelecionada = this.categoriaBolsa.buscarPeloId(id);

        if (this.categoriaBolsaSelecionada == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao v Categoria!",
                                   "Erro ao localizar Categoria!"));
            return "/pages/listar/listarCategoriasBolsa";
        }
        else {
            this.categoriaBolsa = this.categoriaBolsaSelecionada;
            return "/pages/detalhes/detalhesCategoriaBolsa?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            this.categoriaBolsaSelecionada = this.categoriaBolsa.buscarPeloId(id);

        if (this.categoriaBolsaSelecionada == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao v Categoria!",
                                   "Erro ao localizar Categoria!"));
            return "/pages/listar/listarCategoriasBolsa";
        }
        else {
            this.categoriaBolsa = this.categoriaBolsaSelecionada;
            return "/pages/editar/editarCategoriaBolsa?faces-redirect=true";
        }
    }  
    
    public void limpar(){
        this.categoriaBolsa = new CategoriaBolsa();
        this.categoriaBolsaSelecionada = new CategoriaBolsa();
    }
    
    public String remover(Long id) {
        if(this.categoriaBolsa.remover(id))
            return "/pages/listar/listarCategoriasBolsa?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Categoria!",
                                   "Erro ao excluir Categoria!"));
            return "/pages/listar/listarCategoriasBolsa";
        }
    }
    
    public String salvar() {
        if(this.categoriaBolsa.salvar())
            return "/pages/listar/listarCategoriasBolsa?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Categoria!",
                                   "Erro ao salvar Categoria!"));
            return "/pages/cadastrar/cadastrarCategoriaBolsa";
        }
    }
}

