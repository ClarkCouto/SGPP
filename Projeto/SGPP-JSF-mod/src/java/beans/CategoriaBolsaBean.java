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
    private Boolean editando;
    
// Getters e Setters
    public List<CategoriaBolsa> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<CategoriaBolsa> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public CategoriaBolsa getCategoriaBolsa() {
        return categoriaBolsa;
    }

    public void setCategoriaBolsa(CategoriaBolsa categoriaBolsa) {
        this.categoriaBolsa = categoriaBolsa;
    }

    public void setCategoriaBolsaSelecionado(CategoriaBolsa categoriaBolsa) {
        this.categoriaBolsaSelecionada = categoriaBolsa;
    }
      
    public List<CategoriaBolsa> getCategorias(){
        this.categorias = this.categoriaBolsa.buscarTodos();
        return categorias;
    }
    
    public void setCategorias(List<CategoriaBolsa> lista){
        this.categorias = lista;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
// Ações
    public String detalhar(Long id){
        if(id != null)
            categoriaBolsaSelecionada = this.categoriaBolsa.buscarPeloId(id);

        if (categoriaBolsaSelecionada == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar Categoria!",
                                   "Erro ao buscar Categoria!"));
            return "/pages/listar/listarCategoriasBolsa";
        }
        else {
            this.categoriaBolsa = categoriaBolsaSelecionada;
            return "/pages/detalhes/detalhesCategoriaBolsa?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            categoriaBolsaSelecionada = this.categoriaBolsa.buscarPeloId(id);

        if (categoriaBolsaSelecionada != null) {
            this.categoriaBolsa = categoriaBolsaSelecionada;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "/pages/editar/editarCategoriaBolsa?faces-redirect=true";
    }  
    
    public void limpar(){
        this.editando = false;
        this.categoriaBolsa = new CategoriaBolsa();
        this.categoriaBolsaSelecionada = new CategoriaBolsa();
    }
    
    public String remover(Long id) {
        if(categoriaBolsa.remover(id))
            return "/pages/listar/listarCategoriasBolsa?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Categoria!",
                                   "Erro ao excluir Categoria!"));
            return "/pages/listar/listarCategoriasBolsa";
        }
    }
    
    public String salvar() {
        if(categoriaBolsa.salvar())
            return "/pages/listar/listarCategoriasBolsa?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Categoria!",
                                   "Erro ao salvar Categoria!"));
            return "/pages/cadastrar/cadastrarCategoriaBolsa";
        }
    }
}

