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
    public String detalhar(Long id){
        if(id != null)
            cursoSelecionado = this.curso.buscarPeloId(id);

        if (cursoSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar Curso!",
                                   "Erro ao buscar Curso!"));
            return "/pages/listar/listarCursos";
        }
        else {
            this.curso = cursoSelecionado;
            return "/pages/detalhes/detalhesCurso";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            cursoSelecionado = this.curso.buscarPeloId(id);

        if (cursoSelecionado != null) {
            this.curso = cursoSelecionado;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "/pages/editar/editarCurso";
    }  
    
    public void limpar(){
        this.editando = false;
        this.curso = new Curso();
        this.cursoSelecionado = new Curso();
    }
    
    public String remover(Long id) {
        if(curso.remover(id))
            return "/pages/listar/listarCursos?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Curso!",
                                   "Erro ao excluir Curso!"));
            return "/pages/listar/listarCursos";
        }
    }
    
    public String salvar() {
        if(curso.salvar())
            return "/pages/listar/listarCursos?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Curso!",
                                   "Erro ao salvar Curso!"));
            return "/pages/cadastrar/cadastrarCursos";
        }
    }
}

