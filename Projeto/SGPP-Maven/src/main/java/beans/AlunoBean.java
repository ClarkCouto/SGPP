package beans;

import entities.Aluno;
import entities.Bolsa;
import entities.Curso;
import entities.Destinatario;
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
public class AlunoBean {
    private Aluno aluno = new Aluno();
    private Aluno alunoSelecionado;
    private List<Aluno> alunos;
    private List<Aluno> listaFiltrada;
    private List<Bolsa> bolsas;
    private List<Curso> cursos;
    private List<SelectItem> cursosOptions;
    private List<Instituicao> instituicoes;

    public AlunoBean() {
        this.cursos = new ArrayList<>();
    }

// Getters e Setters
    public List<Aluno> getListaFiltrada() {
        return this.listaFiltrada;
    }
 
    public void setListaFiltrada(List<Aluno> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public Aluno getAluno() {
        return this.aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setAlunoSelecionado(Aluno aluno) {
        this.alunoSelecionado = aluno;
    }
      
    public List<Aluno> getAlunos(){
        this.alunos = aluno.buscarTodos();
        return this.alunos;
    }
    
    public void setAlunos(List<Aluno> lista){
        this.alunos = lista;
    }

    public List<SelectItem> getBolsas(){
        this.bolsas = new Bolsa().buscarTodos();
        List<SelectItem> items = new ArrayList<>();  
        this.bolsas.forEach((b) -> {
            items.add(new SelectItem(b, b.getNome()));
        }); 
        return items;
    }

    public List<Curso> getCursos() {
        return this.cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<SelectItem> getCursosOptions() {
        List<Curso> cursosInstituicaoSelecionada = new ArrayList<>();
        if(this.cursosOptions == null){
            if(this.aluno.getInstituicao() != null)
                cursosInstituicaoSelecionada = new Curso().buscarPelaInstituicao(this.aluno.getInstituicao().getId());
            else
                cursosInstituicaoSelecionada = new Curso().buscarTodos();
            List<SelectItem> items = new ArrayList<>();  
            cursosInstituicaoSelecionada.forEach((c) -> {
                items.add(new SelectItem(c, c.getNome()));
            }); 
            return items;
        }
        return this.cursosOptions;
    }

    public void setCursosOptions(List<SelectItem> cursosOptions) {
        this.cursosOptions = cursosOptions;
    }
    
    public List<SelectItem> getInstituicoes(){
        this.instituicoes = new Instituicao().buscarTodos();
        List<SelectItem> items = new ArrayList<>();  
        this.instituicoes.forEach((b) -> {
            items.add(new SelectItem(b, b.getNome()));
        }); 
        return items;
    }
    
// Ações
    public String detalhar(Long id){
        if(id != null)
            this.alunoSelecionado = this.aluno.buscarPeloId(id);

        if (this.alunoSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Aluno!",
                                   "Erro ao localizar Aluno!"));
            return "/pages/listar/listarAlunos";
        }
        else {
            this.aluno = this.alunoSelecionado;
            return "/pages/detalhes/detalhesAluno?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            this.alunoSelecionado = this.aluno.buscarPeloId(id);

        if (this.alunoSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Aluno!",
                                   "Erro ao localizar Aluno!"));
            return "/pages/listar/listarAlunos";
        }
        else {
            this.aluno = this.alunoSelecionado;
            return "/pages/editar/editarAluno?faces-redirect=true";
        }
    }  
    
    public void limpar(){
        this.aluno = new Aluno();
        this.alunoSelecionado = new Aluno();
    }
    
    public String remover(Long id) {
        if(this.aluno.remover(id))
            return "/pages/listar/listarAlunos?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Aluno!",
                                   "Erro ao excluir Aluno!"));
            return "/pages/listar/listarAlunos";
        }
    }
    
    public String salvar() {
        this.aluno.setTipoDestinatario(Destinatario.TipoDestinatario.Aluno);
        this.aluno.setBolsista(Boolean.FALSE);
        if(this.aluno.salvar())
            return "/pages/listar/listarAlunos?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Aluno!",
                                   "Erro ao salvar Aluno!"));
            return "/pages/cadastrar/cadastrarAluno";
        }
    }
    
    public void onInstituicaoChange() {
        List<SelectItem> items = new ArrayList<>();
        this.cursos = new Curso().buscarTodos();
        this.cursos.forEach((c) -> {
            if (this.aluno.getInstituicao().getId() == c.getInstituicao().getId())
                items.add(new SelectItem(c, c.getNome()));
        });
        this.cursosOptions = items;
    }
}

