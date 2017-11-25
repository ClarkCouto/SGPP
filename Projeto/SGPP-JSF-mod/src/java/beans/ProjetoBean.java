package beans;

import entities.Aluno;
import entities.Bolsa;
import entities.Colaborador;
import entities.Coordenador;
import entities.Edital;
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
    private List<Aluno> alunos;
    private List<Bolsa> bolsas;
    private List<Colaborador> colaboradores;
    private List<Coordenador> coordenadores;
    private List<Edital> editais;
    private Boolean editando;
    private List<Projeto> listaFiltrada;
    private List<Projeto> projetos;
    
// Getters e Setters
    public List<Projeto> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<Projeto> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public List<Aluno> getAlunos(){
        this.alunos = (List<Aluno>) (Aluno) new Aluno().buscarTodos();
        return alunos;
    }
    
    public List<Bolsa> getBolsas(){
        this.bolsas = new Bolsa().buscarTodos();
        return bolsas;
    }
    
    public List<Colaborador> getColaboradores(){
        this.colaboradores = new Colaborador().buscarTodos();
        return colaboradores;
    }
    
    public List<Coordenador> getCoordenadores(){
        this.coordenadores = (List<Coordenador>) (Coordenador) new Coordenador().buscarTodos();
        return coordenadores;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
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
    
// Ações
    public String detalhar(Long id){
        if(id != null)
            projetoSelecionado = this.projeto.buscarPeloId(id);

        if (projetoSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Projeto!",
                                   "Erro ao localizar Projeto!"));
            return "/pages/listar/listarProjetos";
        }
        else {
            this.projeto = projetoSelecionado;
            return "/pages/detalhes/detalhesProjeto?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            projetoSelecionado = this.projeto.buscarPeloId(id);

        if (projetoSelecionado != null) {
            this.projeto = projetoSelecionado;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "/pages/editar/editarProjeto?faces-redirect=true";
    }  
    
    public void limpar(){
        this.editando = false;
        this.projeto = new Projeto();
        this.projetoSelecionado = new Projeto();
    }
    
    public String remover(Long id) {
        if(projeto.remover(id))
            return "/pages/listar/listarProjetos?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Projeto!",
                                   "Erro ao excluir Projeto!"));
            return "/pages/listar/listarProjetos";
        }
    }
    
    public String salvar() {
        if(projeto.salvar())
            return "/pages/listar/listarProjetos?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Projeto!",
                                   "Erro ao salvar Projeto!"));
            return "/pages/cadastrar/cadastrarProjeto";
        }
    }
}

