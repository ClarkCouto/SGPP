package beans;

import dao.EditalDAO;
import entities.Bolsa;
import entities.CategoriaBolsa;
import entities.Edital;
import javax.faces.bean.ManagedBean;
import entities.Lembrete;
import entities.TipoDocumento;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author CristianoSilva
 */
@ManagedBean
@SessionScoped
public class EditalBean {
    private Edital edital = new Edital();
    private List<Edital> editais;
    private List<Edital> listaFiltrada;
    private List<Bolsa> bolsas;
    private List<CategoriaBolsa> categoriasBolsa;
    private List<TipoDocumento> tiposDocumento;
    private List<Lembrete> lembretes;

// Construtor
    public EditalBean() {
        this.lembretes = this.edital.getLembretes();
        this.bolsas = this.edital.getBolsas();
        this.editais = new EditalDAO().findAll();
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
        return this.editais;
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
        
    public List<SelectItem> getCategoriasBolsa(){
        this.categoriasBolsa = new CategoriaBolsa().buscarTodos();
        List<SelectItem> items = new ArrayList<>();  
        this.categoriasBolsa.forEach((c) -> {
            items.add(new SelectItem(c, c.getDescricao()));
        }); 
        return items;
    }
    
    public List<Lembrete> getLembretes(){        
        return this.lembretes;
    }
    
    public void setLembretes(List<Lembrete> lista){
        this.lembretes = lista;
    } 
    
    public List<SelectItem> getTiposDocumento(){
        this.tiposDocumento = new TipoDocumento().buscarTodos();
        List<SelectItem> items = new ArrayList<>();  
        this.tiposDocumento.forEach((t) -> {
            items.add(new SelectItem(t, t.getNome()));
        }); 
        return items;
    }   
    
// Ações
    public void adicionarBolsa() {
        this.bolsas.add(new Bolsa());
        this.edital.setBolsas(bolsas);
    }
    
    public void removerBolsa(Long id) {
        this.bolsas = this.bolsas.stream().filter(bolsa -> !Objects.equals(bolsa.getId(), id)).collect(Collectors.toList());
        this.edital.setBolsas(bolsas);
    }
    
    public void adicionarLembrete() {
//        this.lembretes.add(new Lembrete());
        this.lembretes.add(new Lembrete());
        this.edital.setLembretes(lembretes);
    }
    
    public void removerLembrete(Lembrete lembrete) {
        this.lembretes.remove(lembrete);
        this.edital.setLembretes(this.lembretes);
    }   
    
    public void atualizar() {
        this.editais = new EditalDAO().findAll();
    }
    
    public String editar(Long id) {        
        this.edital = new EditalDAO().findById(id);                
        return "/pages/cadastrar/editarEdital?faces-redirect=true&id=" + id;
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

