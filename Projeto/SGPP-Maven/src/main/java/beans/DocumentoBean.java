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
    
// Getters e Setters
    public List<Documento> getListaFiltrada() {
        return this.listaFiltrada;
    }
 
    public void setListaFiltrada(List<Documento> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public Documento getDocumento() {
        return this.documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public void setDocumentoSelecionado(Documento documento) {
        this.documentoSelecionado = documento;
    }
      
    public List<Documento> getDocumentos(){
        this.documentos = this.documento.buscarTodos();
        return this.documentos;
    }
    
    public void setDocumentos(List<Documento> lista){
        this.documentos = lista;
    }
      
    public List<TipoDocumento> getTiposDocumento(){
        this.tiposDocumento = new TipoDocumento().buscarTodos();
        return this.tiposDocumento;
    }
    
// Ações
    
    public String detalhar(Long id){
        if(id != null)
            this.documentoSelecionado = this.documento.buscarPeloId(id);

        if (this.documentoSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Documento!",
                                   "Erro ao localizar Documento!"));
            return "/pages/listar/listarDocumentos";
        }
        else {
            this.documento = this.documentoSelecionado;
            return "/pages/detalhes/detalhesDocumento?faces-redirect=true";
        }
    }
    
    public String salvar() {
        if(this.documento.salvar())
            return "listarDocumentos?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Documento!",
                                   "Erro ao salvar Documento!"));
            return "cadastrarDocumentos";
        }
    }
}

