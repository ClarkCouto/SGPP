package beans;

import entities.TipoDocumento;
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
public class TipoDocumentoBean {
    private TipoDocumento tipoDocumento = new TipoDocumento();
    private TipoDocumento tipoDocumentoSelecionado;
    private List<TipoDocumento> tiposDocumento;
    private List<TipoDocumento> listaFiltrada;
    private Boolean editando;
    
// Getters e Setters
    public List<TipoDocumento> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<TipoDocumento> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setTipoDocumentoSelecionado(TipoDocumento tipoDocumento) {
        this.tipoDocumentoSelecionado = tipoDocumento;
    }
      
    public List<TipoDocumento> getTiposDocumento(){
        this.tiposDocumento = this.tipoDocumento.buscarTodos();
        return tiposDocumento;
    }
    
    public void setTiposDocumento(List<TipoDocumento> lista){
        this.tiposDocumento = lista;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
// Ações
    public String detalhar(Long id){
        if(id != null)
            tipoDocumentoSelecionado = this.tipoDocumento.buscarPeloId(id);

        if (tipoDocumentoSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Tipo de Documento!",
                                   "Erro ao localizar Tipo de Documento!"));
            return "/pages/listar/listarTiposDocumento";
        }
        else {
            this.tipoDocumento = tipoDocumentoSelecionado;
            return "/pages/detalhes/detalhesTipoDocumento?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            tipoDocumentoSelecionado = this.tipoDocumento.buscarPeloId(id);

        if (tipoDocumentoSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Tipo de Documento!",
                                   "Erro ao localizar Tipo de Documento!"));
            return "/pages/listar/listarTiposDocumento";
        }
        else {
            this.tipoDocumento = tipoDocumentoSelecionado;
            return "/pages/editar/editarTipoDocumento?faces-redirect=true";
        }
    }  
    
    public void limpar(){
        this.editando = false;
        this.tipoDocumento = new TipoDocumento();
        this.tipoDocumentoSelecionado = new TipoDocumento();
    }
    
    public String remover(Long id) {
        if(tipoDocumento.remover(id))
            return "/pages/listar/listarTiposDocumento?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Tipo de Documento!",
                                   "Erro ao excluir Tipode Documento!"));
            return "/pages/listar/listarTiposDocumento";
        }
    }
    
    public String salvar() {
        if(tipoDocumento.salvar())
            return "/pages/listar/listarTiposDocumento?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Tipo de Documento!",
                                   "Erro ao salvar Tipo de Documento!"));
            return "/pages/cadastrar/cadastrarTipoDocumento";
        }
    }
}

