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
    private Boolean editando;
    
// Getters e Setters
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
        this.areas = this.area.buscarTodos();
        return areas;
    }
    
    public void setAreas(List<Area> lista){
        this.areas = lista;
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
            areaSelecionada = this.area.buscarPeloId(id);

        if (areaSelecionada != null) {
            this.area = areaSelecionada;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "editarArea";
    }  
    
    public void limpar(){
        this.editando = false;
        this.area = new Area();
        this.areaSelecionada = new Area();
    }
    
    public String remover(Long id) {
        if(area.remover(id))
            return "listarAreas?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Área!",
                                   "Erro ao excluir Área!"));
            return "listarAreas";
        }
    }
    
    public String salvar() {
        if(area.salvar())
            return "listarAreas?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Área!",
                                   "Erro ao salvar Área!"));
            return "cadastrarAreas";
        }
    }
}

