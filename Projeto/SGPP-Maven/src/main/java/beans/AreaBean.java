package beans;

import entities.Area;
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
public class AreaBean {
    private Area area = new Area();
    private Area areaSelecionada;
    private List<Area> areas;
    private List<Area> listaFiltrada;
    
// Getters e Setters
    public List<Area> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<Area> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setAreaSelecionada(Area area) {
        this.areaSelecionada = area;
    }
      
    public List<Area> getAreas(){
        this.areas = area.buscarTodos();
        return areas;
    }
    
    public void setAreas(List<Area> lista){
        this.areas = lista;
    }
    
// Ações
    public String detalhar(Long id){
        if(id != null)
            this.areaSelecionada = area.buscarPeloId(id);

        if (this.areaSelecionada == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Área!",
                                   "Erro ao localizar Área!"));
            return "/pages/listar/listarAreas";
        }
        else {
            this.area = this.areaSelecionada;
            return "/pages/detalhes/detalhesArea?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            this.areaSelecionada = this.area.buscarPeloId(id);

        if (this.areaSelecionada == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Área!",
                                   "Erro ao localizar Área!"));
            return "/pages/listar/listarAreas";
        }
        else {
            this.area = this.areaSelecionada;
            return "/pages/editar/editarArea?faces-redirect=true";
        }
    }  
    
    public void limpar(){
        this.area = new Area();
        this.areaSelecionada = new Area();
    }
    
    public String remover(Long id) {
        if(this.area.remover(id))
            return "/pages/listar/listarAreas?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Área!",
                                   "Erro ao excluir Área!"));
            return "/pages/listar/listarAreas";
        }
    }
    
    public String salvar() {
        if(this.area.salvar())
            return "/pages/listar/listarAreas?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Área!",
                                   "Erro ao salvar Área!"));
            return "/pages/cadastrar/cadastrarArea";
        }
    }
}

