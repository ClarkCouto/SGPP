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
    private List<TextoBaseDeclaracao> textoBaseDeclaracaos;
    private Boolean editando;
    
// Getters e Setters
    public TextoBaseDeclaracao getTextoBaseDeclaracao() {
        return textoBaseDeclaracao;
    }

    public void setTextoBaseDeclaracao(TextoBaseDeclaracao textoBaseDeclaracao) {
        this.textoBaseDeclaracao = textoBaseDeclaracao;
    }

    public void setTextoBaseDeclaracaoSelecionado(TextoBaseDeclaracao textoBaseDeclaracao) {
        this.textoBaseDeclaracaoSelecionado = textoBaseDeclaracao;
    }
      
    public List<TextoBaseDeclaracao> getTextoBaseDeclaracaos(){
        this.textoBaseDeclaracaos = this.textoBaseDeclaracao.buscarTodos();
        return textoBaseDeclaracaos;
    }
    
    public void setTextoBaseDeclaracaos(List<TextoBaseDeclaracao> lista){
        this.textoBaseDeclaracaos = lista;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
// Ações
    public String editar(Long id){
        if(id != null)
            textoBaseDeclaracaoSelecionado = this.textoBaseDeclaracao.buscarPeloId(id);

        if (textoBaseDeclaracaoSelecionado != null) {
            this.textoBaseDeclaracao = textoBaseDeclaracaoSelecionado;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "editarTextoBaseDeclaracao";
    }  
    
    public void limpar(){
        this.editando = false;
        this.textoBaseDeclaracao = new TextoBaseDeclaracao();
        this.textoBaseDeclaracaoSelecionado = new TextoBaseDeclaracao();
    }
    
    public String remover(Long id) {
        if(textoBaseDeclaracao.remover(id))
            return "listarTextosBaseDeclaracao?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Texto Base Declaração!",
                                   "Erro ao excluir Texto Base Declaração!"));
            return "listarTextosBaseDeclaracao";
        }
    }
    
    public String salvar() {
        if(textoBaseDeclaracao.salvar())
            return "listarTextosBaseDeclaracao?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Texto Base Declaração!",
                                   "Erro ao salvar Texto Base Declaração!"));
            return "listarTextosBaseDeclaracao";
        }
    }
}

