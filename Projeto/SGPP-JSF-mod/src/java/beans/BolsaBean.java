package beans;

import entities.Bolsa;
import entities.CategoriaBolsa;
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
public class BolsaBean {
    private Bolsa bolsa = new Bolsa();
    private Bolsa bolsaSelecionada;
    private List<Bolsa> bolsas;
    private List<Bolsa> listaFiltrada;
    private List<CategoriaBolsa> categorias;
    private Boolean editando;
    
// Getters e Setters
    public List<Bolsa> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<Bolsa> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public Bolsa getBolsa() {
        return bolsa;
    }

    public void setBolsa(Bolsa bolsa) {
        this.bolsa = bolsa;
    }

    public void setBolsaSelecionado(Bolsa bolsa) {
        this.bolsaSelecionada = bolsa;
    }
      
    public List<Bolsa> getBolsas(){
        this.bolsas = this.bolsa.buscarTodos();
        return bolsas;
    }
    
    public void setBolsas(List<Bolsa> lista){
        this.bolsas = lista;
    }

    public List<CategoriaBolsa> getCategorias(){
        this.categorias = new CategoriaBolsa().buscarTodos();
        return categorias;
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
            bolsaSelecionada = this.bolsa.buscarPeloId(id);

        if (bolsaSelecionada != null) {
            this.bolsa = bolsaSelecionada;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "/pages/bolsa/editarBolsa";
    }  
    
    public void limpar(){
        this.editando = false;
        this.bolsa = new Bolsa();
        this.bolsaSelecionada = new Bolsa();
    }
    
    public String remover(Long id) {
        if(bolsa.remover(id))
            return "/pages/bolsa/listarBolsas?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Bolsa!",
                                   "Erro ao excluir Bolsa!"));
            return "/pages/bolsa/listarBolsas";
        }
    }
    
    public String salvar() {
        if(bolsa.salvar())
            return "/pages/bolsa/listarBolsas?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Bolsa!",
                                   "Erro ao salvar Bolsa!"));
            return "/pages/bolsa/cadastrarBolsas";
        }
    }
}
