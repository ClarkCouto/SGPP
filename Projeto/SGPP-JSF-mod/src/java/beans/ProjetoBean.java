package beans;

import entities.Projeto;
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
public class ProjetoBean {
    private Projeto projeto = new Projeto();
    private Projeto projetoSelecionado;
    private List<Projeto> projetos;
    private Boolean editando;
    
// Getters e Setters
    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public void setProjetoSelecionado(Projeto projeto) {
        this.projetoSelecionado = projeto;
    }
      
    public List<Projeto> getProjetos(){
        this.projetos = this.projeto.buscarTodos();
        return projetos;
    }
    
    public void setProjetos(List<Projeto> lista){
        this.projetos = lista;
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
            projetoSelecionado = this.projeto.buscarPeloId(id);

        if (projetoSelecionado != null) {
            this.projeto = projetoSelecionado;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "editarProjeto";
    }  
    
    public void limpar(){
        this.editando = false;
        this.projeto = new Projeto();
        this.projetoSelecionado = new Projeto();
    }
    
    public String remover(Long id) {
        if(projeto.remover(id))
            return "listarProjetos?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Projeto!",
                                   "Erro ao excluir Projeto!"));
            return "listarProjetos";
        }
    }
    
    public String salvar() {
        if(projeto.salvar())
            return "listarProjetos?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Projeto!",
                                   "Erro ao salvar Projeto!"));
            return "cadastrarProjetos";
        }
    }
}

