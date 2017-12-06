package beans;

import entities.Curso;
import entities.Instituicao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

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
    
// Getters e Setters
    public List<Curso> getListaFiltrada() {
        return this.listaFiltrada;
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
        return this.cursos;
    }
    
    public void setCursos(List<Curso> lista){
        this.cursos = lista;
    }
    
    public List<SelectItem> getInstituicoes(){
        this.instituicoes = new Instituicao().buscarTodos();
        List<SelectItem> items = new ArrayList<>();  
        this.instituicoes.forEach((c) -> {
            items.add(new SelectItem(c, c.getNome()));
        }); 
        return items;
    }
    
// Ações
    public String detalhar(Long id){
        if(id != null)
            this.cursoSelecionado = this.curso.buscarPeloId(id);

        if (this.cursoSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Curso!",
                                   "Erro ao localizar Curso!"));
            return "/pages/listar/listarCursos";
        }
        else {
            this.curso = this.cursoSelecionado;
            return "/pages/detalhes/detalhesCurso?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            this.cursoSelecionado = this.curso.buscarPeloId(id);

        if (this.cursoSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Curso!",
                                   "Erro ao localizar Curso!"));
            return "/pages/listar/listarCursos";
        }
        else {
            this.curso = this.cursoSelecionado;
            return "/pages/editar/editarCurso?faces-redirect=true";
        }
    }  
    
    public void limpar(){
        this.curso = new Curso();
        this.cursoSelecionado = new Curso();
    }
    
    public String remover(Long id) {
        if(this.curso.remover(id))
            return "/pages/listar/listarCursos?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Curso!",
                                   "Erro ao excluir Curso!"));
            return "/pages/listar/listarCursos";
        }
    }
    
    public String salvar() {
        if(this.curso.salvar())
            return "/pages/listar/listarCursos?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Curso!",
                                   "Erro ao salvar Curso!"));
            return "/pages/cadastrar/cadastrarCurso";
        }
    }
}

