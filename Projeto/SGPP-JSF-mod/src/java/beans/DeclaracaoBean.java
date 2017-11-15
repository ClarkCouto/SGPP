package beans;

import entities.Declaracao;
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
public class DeclaracaoBean {
    private Declaracao declaracao = new Declaracao();
    private Declaracao declaracaoSelecionada;
    private List<Declaracao> declaracaos;
    private Boolean editando;
    
// Getters e Setters
    public Declaracao getDeclaracao() {
        return declaracao;
    }

    public void setDeclaracao(Declaracao declaracao) {
        this.declaracao = declaracao;
    }

    public void setDeclaracaoSelecionado(Declaracao declaracao) {
        this.declaracaoSelecionada = declaracao;
    }
      
    public List<Declaracao> getDeclaracaos(){
        this.declaracaos = this.declaracao.buscarTodos();
        return declaracaos;
    }
    
    public void setDeclaracaos(List<Declaracao> lista){
        this.declaracaos = lista;
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
            declaracaoSelecionada = this.declaracao.buscarPeloId(id);

        if (declaracaoSelecionada != null) {
            this.declaracao = declaracaoSelecionada;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "editarDeclaracao";
    }  
    
    public void limpar(){
        this.editando = false;
        this.declaracao = new Declaracao();
        this.declaracaoSelecionada = new Declaracao();
    }
    
    public String remover(Long id) {
        if(declaracao.remover(id))
            return "listarDeclaracoes?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Declaração!",
                                   "Erro ao excluir Declaração!"));
            return "listarDeclaracoes";
        }
    }
    
    public String salvar() {
        if(declaracao.salvar())
            return "listarDeclaracoes?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Declaração!",
                                   "Erro ao salvar Declaração!"));
            return "cadastrarDeclaracoes";
        }
    }
}

