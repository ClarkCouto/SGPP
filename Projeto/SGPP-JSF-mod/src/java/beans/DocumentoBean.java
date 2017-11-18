package beans;

import entities.Documento;
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
public class DocumentoBean {
    private Documento documento = new Documento();
    private Documento documentoSelecionado;
    private List<Documento> documentos;
    private List<Documento> listaFiltrada;
    private List<TipoDocumento> tiposDocumento;
    private Boolean editando;
    
// Getters e Setters
    public List<Documento> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<Documento> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public void setDocumentoSelecionado(Documento documento) {
        this.documentoSelecionado = documento;
    }
      
    public List<Documento> getDocumentos(){
        this.documentos = this.documento.buscarTodos();
        return documentos;
    }
    
    public void setDocumentos(List<Documento> lista){
        this.documentos = lista;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
      
    public List<TipoDocumento> getTiposDocumento(){
        this.tiposDocumento = new TipoDocumento().buscarTodos();
        return tiposDocumento;
    }
    
// Ações
    public String editar(Long id){
        if(id != null)
            documentoSelecionado = this.documento.buscarPeloId(id);

        if (documentoSelecionado != null) {
            this.documento = documentoSelecionado;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "editarDocumento";
    }  
    
    public void limpar(){
        this.editando = false;
        this.documento = new Documento();
        this.documentoSelecionado = new Documento();
    }
    
    public String remover(Long id) {
        if(documento.remover(id))
            return "listarDocumentos?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Documento!",
                                   "Erro ao excluir Documento!"));
            return "listarDocumentos";
        }
    }
    
    public String salvar() {
        if(documento.salvar())
            return "listarDocumentos?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Documento!",
                                   "Erro ao salvar Documento!"));
            return "cadastrarDocumentos";
        }
    }
}

