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
    private List<Instituicao> instituicaos;
    private Boolean editando;
    
// Getters e Setters
    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public void setInstituicaoSelecionado(Instituicao instituicao) {
        this.instituicaoSelecionada = instituicao;
    }
      
    public List<Instituicao> getInstituicaos(){
        this.instituicaos = this.instituicao.buscarTodos();
        return instituicaos;
    }
    
    public void setInstituicaos(List<Instituicao> lista){
        this.instituicaos = lista;
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
            instituicaoSelecionada = this.instituicao.buscarPeloId(id);

        if (instituicaoSelecionada != null) {
            this.instituicao = instituicaoSelecionada;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "editarInstituicao";
    }  
    
    public void limpar(){
        this.editando = false;
        this.instituicao = new Instituicao();
        this.instituicaoSelecionada = new Instituicao();
    }
    
    public String remover(Long id) {
        if(instituicao.remover(id))
            return "listarInstituições?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Instituição!",
                                   "Erro ao excluir Instituição!"));
            return "listarInstituições";
        }
    }
    
    public String salvar() {
        if(instituicao.salvar())
            return "listarInstituições?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Instituição!",
                                   "Erro ao salvar Instituição!"));
            return "cadastrarInstituições";
        }
    }
}

