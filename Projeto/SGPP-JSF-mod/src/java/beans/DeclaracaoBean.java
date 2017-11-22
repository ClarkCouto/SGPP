package beans;

import entities.Aluno;
import entities.Cagppi;
import entities.Coordenador;
import entities.Declaracao;
import entities.Pessoa;
import entities.Projeto;
import entities.SetorDePesquisa;
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
public class DeclaracaoBean {
    private Declaracao declaracao = new Declaracao();
    private Declaracao declaracaoSelecionada;
    private List<Declaracao> declaracoes;
    private List<Declaracao> listaFiltrada;
    private List<Pessoa> coordenadores;
    private List<Projeto> projetos;
    private List<Pessoa> pessoas;
    private List<TextoBaseDeclaracao> textosBase;
    private Boolean editando;
    
// Getters e Setters
    public List<Declaracao> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<Declaracao> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public List<Pessoa> getCoordenadores(){
        this.coordenadores = new Coordenador().buscarTodos();
        return coordenadores;
    }
    
    public Declaracao getDeclaracao() {
        return declaracao;
    }

    public void setDeclaracao(Declaracao declaracao) {
        this.declaracao = declaracao;
    }

    public void setDeclaracaoSelecionado(Declaracao declaracao) {
        this.declaracaoSelecionada = declaracao;
    }
      
    public List<Declaracao> getDeclaracoes(){
        this.declaracoes = this.declaracao.buscarTodos();
        return declaracoes;
    }
    
    public void setDeclaracoes(List<Declaracao> lista){
        this.declaracoes = lista;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
      
    public List<Pessoa> getPessoas(){
        List<Pessoa> listaAlunos = new Aluno().buscarTodos();
        List<Pessoa> listaCoordenadores = new Coordenador().buscarTodos();
        List<Pessoa> listaCagppi = new Cagppi().buscarTodos();
        List<Pessoa> listaSetorDePesquisa = new SetorDePesquisa().buscarTodos();
        pessoas.addAll(listaAlunos);
        pessoas.addAll(listaCoordenadores);
        pessoas.addAll(listaCagppi);
        pessoas.addAll(listaSetorDePesquisa);
        return pessoas;
    }
      
    public List<Projeto> getProjetos(){
        this.projetos = new Projeto().buscarTodos();
        return projetos;
    }
      
    public List<TextoBaseDeclaracao> getTextosBase(){
        this.textosBase = new TextoBaseDeclaracao().buscarTodos();
        return textosBase;
    }
    
// Ações
    public String detalhar(Long id){
        if(id != null)
            declaracaoSelecionada = this.declaracao.buscarPeloId(id);

        if (declaracaoSelecionada == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar Declaração!",
                                   "Erro ao buscar Declaração!"));
            return "/pages/listar/listarDeclaracoes";
        }
        else {
            this.declaracao = declaracaoSelecionada;
            return "/pages/detalhes/detalhesDeclaracao?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            declaracaoSelecionada = this.declaracao.buscarPeloId(id);

        if (declaracaoSelecionada != null) {
            this.declaracao = declaracaoSelecionada;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "/pages/editar/editarDeclaracao?faces-redirect=true";
    }  
    
    public void limpar(){
        this.editando = false;
        this.declaracao = new Declaracao();
        this.declaracaoSelecionada = new Declaracao();
    }
    
    public String remover(Long id) {
        if(declaracao.remover(id))
            return "/pages/listar/listarDeclaracoes?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Declaração!",
                                   "Erro ao excluir Declaração!"));
            return "/pages/listar/listarDeclaracoes";
        }
    }
    
    public String salvar() {
        if(declaracao.salvar())
            return "/pages/listar/listarDeclaracoes?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Declaração!",
                                   "Erro ao salvar Declaração!"));
            return "/pages/cadastrar/cadastrarDeclaracao";
        }
    }
}

