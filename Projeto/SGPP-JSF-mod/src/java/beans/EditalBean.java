package beans;

import dao.EditalDAO;
import entities.Bolsa;
import entities.CategoriaBolsa;
import entities.Edital;
import javax.faces.bean.ManagedBean;
import entities.Lembrete;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author CristianoSilva
 */
@ManagedBean
@ViewScoped
public class EditalBean {
    private Edital edital = new Edital();
    private Edital editalSelecionado = new Edital();
    private List<Bolsa> bolsas = new ArrayList<>();
    private List<CategoriaBolsa> categoriasBolsa;
    private List<Edital> editais;
    private List<Edital> listaFiltrada;
    private List<Lembrete> lembretes = new ArrayList<>();

// Construtor
    public EditalBean() {
        this.lembretes = this.edital.getLembretes();
        this.bolsas = this.edital.getBolsas();
        if(this.lembretes == null)
            this.lembretes = new ArrayList<>();
        if(this.bolsas == null)
            this.bolsas = new ArrayList<>();
    }
       
// Getters e Setters
    public List<Edital> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<Edital> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }
    
    public List<Edital> getEditais() {
        if(this.editais == null)
            this.editais = new EditalDAO().findAll();
        return this.editais;
    }  

    public void setEditalSelecionado(Edital edital) {
        this.editalSelecionado = edital;
    }
        
    public List<Bolsa> getBolsas(){
        this.bolsas = this.edital.getBolsas();
        if(this.bolsas == null)
            this.bolsas = new ArrayList<>();
        return bolsas;
    }
    
    public void setBolsas(List<Bolsa> lista){
        this.bolsas = lista;
    }
    
    public List<Lembrete> getLembretes(){        
        return lembretes;
    }
    
    public void setLembretes(List<Lembrete> lista){
        this.lembretes = lista;
    }    
    
// Ações
    public void adicionarBolsa() {
        this.bolsas.add(new Bolsa());
        this.edital.setBolsas(bolsas);
    }
    
    public void removerBolsa(Bolsa bolsa) {
        this.bolsas.remove(bolsa);
        this.edital.setBolsas(bolsas);
    }
    
    public void adicionarLembrete() {
//        this.lembretes.add(new Lembrete());
        this.edital.getLembretes().add(new Lembrete());
    }
    
    public void removerLembrete(Lembrete lembrete) {
//        this.lembretes.remove(lembrete);
        this.edital.getLembretes().remove(new Lembrete());
    }   
    
    public String editar(Long id) {        
        return "/pages/cadastrar/cadastrarEdital?faces-redirect=true&id="+id;
    } 
    
    public void limpar(Long id){
        if (id != null) {   
            edital = new Edital().buscarPeloId(id);
            
            if (edital == null)
                edital =  new Edital();
        }
    }
    
    public String remover(Long id) {
        if(edital.remover(id))
            return "/pages/listar/listarEditais?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir Edital!",
                                   "Erro ao excluir Edital!"));
            return "/pages/listar/listarEditais";
        }
    }
    
    public String salvar() {
        this.edital.setLembretes(lembretes);
        this.edital.setBolsas(bolsas);
        if(edital.salvar())
            return "/pages/listar/listarEditais?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Edital!",
                                   "Erro ao salvar Edital!"));
            return "/pages/listar/cadastrarEditais";
        }
    }
}

