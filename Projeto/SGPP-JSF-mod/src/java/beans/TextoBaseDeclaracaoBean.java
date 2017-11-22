package beans;

import entities.TextoBaseDeclaracao;
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
public class TextoBaseDeclaracaoBean {
    private TextoBaseDeclaracao textoBaseDeclaracao = new TextoBaseDeclaracao();
    private TextoBaseDeclaracao textoBaseDeclaracaoSelecionado;
    private List<TextoBaseDeclaracao> listaFiltrada;
    private List<TextoBaseDeclaracao> textosBaseDeclaracao;
    private Boolean editando;
    
// Getters e Setters
    public List<TextoBaseDeclaracao> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<TextoBaseDeclaracao> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public TextoBaseDeclaracao getTextoBaseDeclaracao() {
        return textoBaseDeclaracao;
    }

    public void setTextoBaseDeclaracao(TextoBaseDeclaracao textoBaseDeclaracao) {
        this.textoBaseDeclaracao = textoBaseDeclaracao;
    }

    public void setTextoBaseDeclaracaoSelecionado(TextoBaseDeclaracao textoBaseDeclaracao) {
        this.textoBaseDeclaracaoSelecionado = textoBaseDeclaracao;
    }
      
    public List<TextoBaseDeclaracao> getTextosBaseDeclaracao(){
        this.textosBaseDeclaracao = this.textoBaseDeclaracao.buscarTodos();
        return textosBaseDeclaracao;
    }
    
    public void setTextosBaseDeclaracao(List<TextoBaseDeclaracao> lista){
        this.textosBaseDeclaracao = lista;
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
            textoBaseDeclaracaoSelecionado = this.textoBaseDeclaracao.buscarPeloId(id);

        if (textoBaseDeclaracaoSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar Texto Base Declaração!",
                                   "Erro ao buscar Texto Base Declaração!"));
            return "/pages/listar/listarTextosBaseDeclaracao";
        }
        else {
            this.textoBaseDeclaracao = textoBaseDeclaracaoSelecionado;
            return "/pages/detalhes/detalhesTextoBaseDeclaracao?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            textoBaseDeclaracaoSelecionado = this.textoBaseDeclaracao.buscarPeloId(id);

        if (textoBaseDeclaracaoSelecionado != null) {
            this.textoBaseDeclaracao = textoBaseDeclaracaoSelecionado;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "/pages/editar/editarTextoBaseDeclaracao?faces-redirect=true";
    }  
    
    public void limpar(){
        this.editando = false;
        this.textoBaseDeclaracao = new TextoBaseDeclaracao();
        this.textoBaseDeclaracaoSelecionado = new TextoBaseDeclaracao();
    }
    
    public String remover(Long id) {
        if(textoBaseDeclaracao.remover(id))
            return "/pages/listar/listarTextosBaseDeclaracao?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Texto Base Declaração!",
                                   "Erro ao excluir Texto Base Declaração!"));
            return "/pages/listar/listarTextosBaseDeclaracao";
        }
    }
    
    public String salvar() {
        if(textoBaseDeclaracao.salvar())
            return "/pages/listar/listarTextosBaseDeclaracao?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Texto Base Declaração!",
                                   "Erro ao salvar Texto Base Declaração!"));
            return "/pages/cadastrar/cadastrarTextoBaseDeclaracao";
        }
    }
}

