package beans;

import entities.Colaborador;
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
public class ColaboradorBean {
    private Colaborador colaborador = new Colaborador();
    private Colaborador colaboradorSelecionado;
    private List<Colaborador> colaboradors;
    private Boolean editando;
    
// Getters e Setters
    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public void setColaboradorSelecionado(Colaborador colaborador) {
        this.colaboradorSelecionado = colaborador;
    }
      
    public List<Colaborador> getColaboradors(){
        this.colaboradors = this.colaborador.buscarTodos();
        return colaboradors;
    }
    
    public void setColaboradors(List<Colaborador> lista){
        this.colaboradors = lista;
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
            colaboradorSelecionado = this.colaborador.buscarPeloId(id);

        if (colaboradorSelecionado != null) {
            this.colaborador = colaboradorSelecionado;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "editarColaborador";
    }  
    
    public void limpar(){
        this.editando = false;
        this.colaborador = new Colaborador();
        this.colaboradorSelecionado = new Colaborador();
    }
    
    public String remover(Long id) {
        if(colaborador.remover(id))
            return "listarColaboradores?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Colaborador!",
                                   "Erro ao excluir Colaborador!"));
            return "listarColaboradores";
        }
    }
    
    public String salvar() {
        if(colaborador.salvar())
            return "listarColaboradores?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Colaborador!",
                                   "Erro ao salvar Colaborador!"));
            return "cadastrarColaborador";
        }
    }
}

