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
    private List<TipoDocumento> tipoDocumentos;
    private Boolean editando;
    
// Getters e Setters
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setTipoDocumentoSelecionado(TipoDocumento tipoDocumento) {
        this.tipoDocumentoSelecionado = tipoDocumento;
    }
      
    public List<TipoDocumento> getTipoDocumentos(){
        this.tipoDocumentos = this.tipoDocumento.buscarTodos();
        return tipoDocumentos;
    }
    
    public void setTipoDocumentos(List<TipoDocumento> lista){
        this.tipoDocumentos = lista;
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
            tipoDocumentoSelecionado = this.tipoDocumento.buscarPeloId(id);

        if (tipoDocumentoSelecionado != null) {
            this.tipoDocumento = tipoDocumentoSelecionado;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "editarTipoDocumento";
    }  
    
    public void limpar(){
        this.editando = false;
        this.tipoDocumento = new TipoDocumento();
        this.tipoDocumentoSelecionado = new TipoDocumento();
    }
    
    public String remover(Long id) {
        if(tipoDocumento.remover(id))
            return "listarTiposDocumento?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Tipo de Documento!",
                                   "Erro ao excluir Tipode Documento!"));
            return "listarTiposDocumento";
        }
    }
    
    public String salvar() {
        if(tipoDocumento.salvar())
            return "listarTiposDocumeto?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Tipo de Documento!",
                                   "Erro ao salvar Tipo de Documento!"));
            return "cadastrarTiposDocumento";
        }
    }
}

