package beans;

import entities.Aluno;
import entities.Bolsa;
import entities.Colaborador;
import entities.Coordenador;
import entities.Documento;
import entities.Edital;
import entities.Lembrete;
import entities.Projeto;
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
public class ProjetoBean {
    private Projeto projeto = new Projeto();
    private Projeto projetoSelecionado;
    private List<Aluno> alunos;
    private List<Aluno> alunosARemover = new ArrayList<>();
    private List<Colaborador> colaboradores;
    private List<Colaborador> colaboradoresARemover = new ArrayList<>();
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
        this.alunos = this.projeto.getListaAlunos();
        if(this.alunos == null)
            this.alunos = new ArrayList<>();
        return this.alunos;
    }
    
    public void setAlunos(List<Aluno> alunos){
        this.alunos = alunos;
    }
    
    public List<SelectItem> getAlunosOption(){       
        List<SelectItem> items = new ArrayList<>(); 
        new Aluno().buscarTodos().forEach((a) -> {
            items.add(new SelectItem(a, a.getNome()));
        }); 
        return items;
    }

    public List<SelectItem> getBolsas() {
        List<SelectItem> items = new ArrayList<>();
        new Bolsa().buscarTodos().forEach((b) -> {
            items.add(new SelectItem(b, b.getNome()));
        });
        return items;
    }

    public List<Colaborador> getColaboradores() {
        this.colaboradores = this.projeto.getListaColaboradores();
        if (this.colaboradores == null) {
            this.colaboradores = new ArrayList<>();
        }
        return this.colaboradores;
    }

    public List<SelectItem> getCoordenadores() {
        List<SelectItem> items = new ArrayList<>();
        new Coordenador().buscarTodosCoordenadores().forEach((c) -> {
            items.add(new SelectItem(c, c.getNome()));
        });
        return items;
    }

    public List<SelectItem> getEditais() {
        List<SelectItem> items = new ArrayList<>();
        new Edital().buscarTodos().forEach((e) -> {
            items.add(new SelectItem(e, e.getTitulo()));
        });
        return items;
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

    public List<Projeto> getProjetos() {
        this.projetos = this.projeto.buscarTodos();
        return projetos;
    }

    public void setProjetos(List<Projeto> lista) {
        this.projetos = lista;
    }

// Ações
    public String detalhar(Long id) {
        if (id != null) {
            projetoSelecionado = this.projeto.buscarPeloId(id);
        }

        if (projetoSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Projeto!",
                            "Erro ao localizar Projeto!"));
            return "/pages/listar/listarProjetos";
        } else {
            this.projeto = projetoSelecionado;
            return "/pages/detalhes/detalhesProjeto?faces-redirect=true";
        }
    }

    public String cadastrar() {
        limpar();
        return "/pages/cadastrar/cadastrarProjeto?faces-redirect=true";
    }

    public String editar(Long id) {
        if (id != null) {
            projetoSelecionado = this.projeto.buscarPeloId(id);
        }

        if (projetoSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Projeto!",
                            "Erro ao localizar Projeto!"));
            return "/pages/listar/listarProjetos";
        } else {
            this.projeto = projetoSelecionado;
            return "/pages/editar/editarProjeto?faces-redirect=true";
        }
    }

    public void limpar() {
        this.projeto = new Projeto();
        this.projetoSelecionado = new Projeto();
    }

    public String remover(Long id) {
        if (retirarBolsasDosAlunosExcluidos(this.alunosARemover) && 
            desabilitarColaboradoresExcluidos(this.colaboradoresARemover)){
            this.projeto.remover(id);
            return "/pages/listar/listarProjetos?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Projeto!",
                            "Erro ao excluir Projeto!"));
            return "/pages/listar/listarProjetos";
        }
    }

    public String salvar() {
        List<Aluno> lista = ajustarAlunoInseridos();
        this.projeto.setListaAlunos(lista);
        if(this.projeto.getId() == null)
            copiarLembretesEDocumentosDoEdital();
        if(this.projeto.salvar()){
            retirarBolsasDosAlunosExcluidos(this.alunosARemover);
            desabilitarColaboradoresExcluidos(this.colaboradoresARemover);
            atualizarAlunos(lista);
            return "/pages/listar/listarProjetos?faces-redirect=true";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Projeto!",
                            "Erro ao salvar Projeto!"));
            return "/pages/cadastrar/cadastrarProjeto";
        }
    }
    
    public void copiarLembretesEDocumentosDoEdital(){
        List<Lembrete> lembretes = new Lembrete().buscarLembretesDetachedAtravesDoEdital(this.projeto.getEdital().getId());
        lembretes.forEach((l) -> {
            l.setId(null);
            if(l.tipoDocumento != null){
                Documento documento = new Documento();
                documento.setAtivo(Boolean.TRUE);
                documento.setEntregue(Boolean.FALSE);
                documento.setTipoDocumento(l.tipoDocumento);
                l.setDocumento(documento);
            }
        });
        this.projeto.setLembretes(lembretes);
    }
    
    public List<Aluno> ajustarAlunoInseridos(){
        List<Aluno> lista = new ArrayList<>();
        this.alunos.forEach((a) -> {
            Aluno aluno = new Aluno().buscarPeloId(Long.parseLong(a.getNome().replaceAll("[^\\d]", "")));
            aluno.setBolsa(a.getBolsa());
            aluno.setBolsista(a.getBolsista());
            lista.add(aluno);
        });
        return lista;
    }
    
    public void atualizarAlunos(List<Aluno> lista){
        lista.forEach((a) -> {
            a.salvar();
        });
    }
    
    public boolean retirarBolsasDosAlunosExcluidos(List<Aluno> lista){
        try{
            if(lista != null){
                lista.forEach((a) -> {
                    a.setBolsista(Boolean.FALSE);
                    a.setBolsa(null);
                    a.salvar();
                });
            }
            return true;
        }
        catch(Exception e){
        }
        return false;
    }
    
    public boolean desabilitarColaboradoresExcluidos(List<Colaborador> lista){
        try{
            if(lista != null){
                lista.forEach((c) -> {
                    c.setAtivo(Boolean.FALSE);
                    c.salvar();
                });
            }
            return true;
        }
        catch(Exception e){
        }
        return false;
    }
    
    public boolean desabilitarLembretesDoProjeto(List<Lembrete> lista){
        try{
            if(lista != null){
                lista.forEach((l) -> {
                    l.setAtivo(Boolean.FALSE);
                    l.salvar();
                });
            }
            return true;
        }
        catch(Exception e){
        }
        return false;
    }
    
    public boolean desabilitarDocumentosDoProjeto(List<Documento> lista){
        try{
            if(lista != null){
                lista.forEach((d) -> {
                    d.setAtivo(Boolean.FALSE);
                    d.salvar();
                });
            }
            return true;
        }
        catch(Exception e){
        }
        return false;
    }
    
    public void adicionarAluno(){
        this.alunos.add(new Aluno());
        this.projeto.setListaAlunos(this.alunos);
    }
    
    public void removerAluno(Aluno a) {
        this.alunos.remove(a);
        this.alunosARemover.add(a);
        this.projeto.setListaAlunos(this.alunos);
    }

    public void adicionarColaborador() {
        this.colaboradores.add(new Colaborador());
        this.projeto.setListaColaboradores(this.colaboradores);
    }

    public void removerColaborador(Colaborador c) {
        this.colaboradores.remove(c);
        this.colaboradoresARemover.add(c);
        this.projeto.setListaColaboradores(colaboradores);
    }
}
