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
    private List<Colaborador> colaboradores;
    private List<Colaborador> listaFiltrada;
    
// Getters e Setters
    public List<Colaborador> getListaFiltrada() {
        return this.listaFiltrada;
    }
 
    public void setListaFiltrada(List<Colaborador> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public Colaborador getColaborador() {
        return this.colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public void setColaboradorSelecionado(Colaborador colaborador) {
        this.colaboradorSelecionado = colaborador;
    }
      
    public List<Colaborador> getColaboradores(){
        this.colaboradores = this.colaborador.buscarTodos();
        return this.colaboradores;
    }
    
    public void setColaboradores(List<Colaborador> lista){
        this.colaboradores = lista;
    }
    
// Ações
    public String detalhar(Long id){
        if(id != null)
            this.colaboradorSelecionado = this.colaborador.buscarPeloId(id);

        if (this.colaboradorSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Colaborador!",
                                   "Erro ao localizar Colaborador!"));
            return "/pages/listar/listarColaboradores";
        }
        else {
            this.colaborador = this.colaboradorSelecionado;
            return "/pages/detalhes/detalhesColaborador?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            this.colaboradorSelecionado = this.colaborador.buscarPeloId(id);

        if (this.colaboradorSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Colaborador!",
                                   "Erro ao localizar Colaborador!"));
            return "/pages/listar/listarColaboradores";
        }
        else {
            this.colaborador = this.colaboradorSelecionado;
            return "/pages/editar/editarColaborador?faces-redirect=true";
        }
    }  
    
    public void limpar(){
        this.colaborador = new Colaborador();
        this.colaboradorSelecionado = new Colaborador();
    }
    
    public String remover(Long id) {
        if(this.colaborador.remover(id))
            return "/pages/listar/listarColaboradores?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Colaborador!",
                                   "Erro ao excluir Colaborador!"));
            return "/pages/listar/listarColaboradores";
        }
    }
    
    public String salvar() {
        if(this.colaborador.salvar())
            return "/pages/listar/listarColaboradores?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Colaborador!",
                                   "Erro ao salvar Colaborador!"));
            return "/pages/cadastrar/cadastrarColaborador";
        }
    }
}

