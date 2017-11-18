package beans;

import entities.Curso;
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
public class CursoBean {
    private Curso curso = new Curso();
    private Curso cursoSelecionado;
    private List<Curso> cursos;
    private List<Curso> listaFiltrada;
    private List<Instituicao> instituicoes;
    private Boolean editando;
    
// Getters e Setters
    public List<Curso> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<Curso> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setCursoSelecionado(Curso curso) {
        this.cursoSelecionado = curso;
    }
      
    public List<Curso> getCursos(){
        this.cursos = this.curso.buscarTodos();
        return cursos;
    }
    
    public void setCursos(List<Curso> lista){
        this.cursos = lista;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
    public List<Instituicao> getInstituicoes(){
        this.instituicoes = new Instituicao().buscarTodos();
        return instituicoes;
    }
    
// Ações
    public String editar(Long id){
        if(id != null)
            cursoSelecionado = this.curso.buscarPeloId(id);

        if (cursoSelecionado != null) {
            this.curso = cursoSelecionado;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "/pages/curso/editarCurso";
    }  
    
    public void limpar(){
        this.editando = false;
        this.curso = new Curso();
        this.cursoSelecionado = new Curso();
    }
    
    public String remover(Long id) {
        if(curso.remover(id))
            return "/pages/curso/listarCursos?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Curso!",
                                   "Erro ao excluir Curso!"));
            return "/pages/curso/listarCursos";
        }
    }
    
    public String salvar() {
        if(curso.salvar())
            return "/pages/curso/listarCursos?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Curso!",
                                   "Erro ao salvar Curso!"));
            return "/pages/curso/cadastrarCursos";
        }
    }
}

