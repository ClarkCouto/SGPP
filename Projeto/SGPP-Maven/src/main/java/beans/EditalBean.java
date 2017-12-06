package beans;

import entities.Bolsa;
import entities.CategoriaBolsa;
import entities.Documento;
import entities.Edital;
import javax.faces.bean.ManagedBean;
import entities.Lembrete;
import entities.TipoDocumento;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
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
    private Edital editalSelecionado;
    private List<Edital> editais;
    private List<Edital> listaFiltrada;
    private List<Bolsa> bolsas;
    private List<Bolsa> bolsasARemover = new ArrayList<>();
    private List<CategoriaBolsa> categoriasBolsa;
    private List<TipoDocumento> tiposDocumento;
    private List<Lembrete> lembretes;
    private List<Lembrete> lembretesARemover = new ArrayList<>();
       
// Getters e Setters
    public List<Edital> getListaFiltrada() {
        return this.listaFiltrada;
    }
 
    public void setListaFiltrada(List<Edital> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public Edital getEdital() {
        return this.edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }
    
    public List<Edital> getEditais() {    
        this.editais = this.edital.buscarTodos();
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
        this.lembretes = this.edital.getLembretes();
        if(this.lembretes == null)
            this.lembretes = new ArrayList<>();   
        return this.lembretes;
    }
    
    public void setLembretes(List<Lembrete> lista){
        this.lembretes = lista;
    } 
    
    public List<SelectItem> getTiposDocumento(){
        if(this.tiposDocumento == null)
            this.tiposDocumento = new TipoDocumento().buscarTodos();
        List<SelectItem> items = new ArrayList<>();  
        this.tiposDocumento.forEach((t) -> {
            items.add(new SelectItem(t, t.getNome()));
        }); 
        return items;
    }   
    
// Ações
    public String cadastrar(){
        limpar();
        return "/pages/cadastrar/cadastrarEdital?faces-redirect=true";
    }
    
    public String detalhar(Long id){
        if(id != null)
            this.editalSelecionado = this.edital.buscarPeloId(id);

        if (this.editalSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Edital!",
                                   "Erro ao localizar Edital!"));
            return "/pages/listar/listarEditais";
        }
        else {
            this.edital = this.editalSelecionado;
            return "/pages/detalhes/detalhesEdital?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            this.editalSelecionado = this.edital.buscarPeloId(id);

        if (this.editalSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Edital!",
                                   "Erro ao localizar Edital!"));
            return "/pages/listar/listarEditais";
        }
        else {
            this.edital = this.editalSelecionado;
            return "/pages/editar/editarEdital?faces-redirect=true";
        }
    }  
    
    public void limpar(){
        this.bolsas = new ArrayList<>();  
        this.edital = new Edital();
        this.editalSelecionado = new Edital();
        this.lembretes = new ArrayList<>();  
    }
    
    public void adicionarBolsa() {
        this.bolsas.add(new Bolsa());
        this.edital.setBolsas(this.bolsas);
    }
    
    public void removerBolsa(Bolsa b) {
        this.bolsas.remove(b);
        this.bolsasARemover.add(b);
        this.edital.setBolsas(this.bolsas);
    }
    
    public boolean desabilitarBolsas(List<Bolsa> lista){
        try{
            if(lista != null){
                lista.forEach((b) -> {
                    b.setAtivo(Boolean.FALSE);
                    b.salvar();
                });
            }
            return true;
        }
        catch(Exception e){
        }
        return false;
    }
    
    public void adicionarLembrete() {
        this.lembretes.add(new Lembrete());
        this.edital.setLembretes(this.lembretes);
    }
    
    public void removerLembrete(Lembrete l) {
        this.lembretes.remove(l);
        this.lembretesARemover.add(l);
        this.edital.setLembretes(this.lembretes);
    } 
    
    public boolean desabilitarLembretes(List<Lembrete> lista){
        try{
            if(lista != null){
                lista.forEach((l) -> {
                    if(l.tipoDocumento != null){
                        Documento documento = l.getDocumento();
                        if(documento != null){
                            documento.setAtivo(Boolean.FALSE);
                        }
                        l.setDocumento(documento);
                    }
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

    public String remover(Long id) {
        this.edital = this.edital.buscarPeloId(id);
        if(desabilitarBolsas(getBolsas()) && desabilitarLembretes(getLembretes())){
            this.edital.setLembretes(this.lembretes);
            this.edital.setBolsas(this.bolsas);
            if(this.edital.remover(id)){
                return "/pages/listar/listarEditais?faces-redirect=true";
            }
        }
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir Edital!",
                    "Erro ao excluir Edital!"));
        return "/pages/listar/listarEditais";
    }
    
    public String salvar() {
        adicionarDocumentosCadastrados();
        this.edital.setLembretes(this.lembretes);
        this.edital.setBolsas(this.bolsas);
        if(this.edital.salvar()){
            desabilitarBolsas(this.bolsasARemover);
            desabilitarLembretes(this.lembretesARemover);
            return "/pages/listar/listarEditais?faces-redirect=true";
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Edital!",
                                   "Erro ao salvar Edital!"));
            return "/pages/listar/cadastrarEditais";
        }
    }
    
    public void adicionarDocumentosCadastrados(){
        this.lembretes.forEach((l) -> {
            if(l.tipoDocumento != null){
                Documento documento = l.getDocumento() != null ? l.getDocumento() : new Documento();
                documento.setAtivo(Boolean.TRUE);
                documento.setEntregue(Boolean.FALSE);
                documento.setTipoDocumento(l.tipoDocumento);
                l.setDocumento(documento);
            }
        }); 
    }
    
    public boolean possuiProjetos(Long id){
        return this.edital.possuiProjetos(id);
    }
}

