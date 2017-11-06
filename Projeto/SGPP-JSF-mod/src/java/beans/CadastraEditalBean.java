package beans;

import dao.EditalDAO;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import entities.Edital;
import entities.Lembrete;
import java.io.Serializable;
import java.util.LinkedList;

@ManagedBean
@ViewScoped
public class CadastraEditalBean implements Serializable {    
    
    private Edital edital;
    private Boolean isEditando;

// Construtor

    public CadastraEditalBean() {
        this.edital = new Edital();
        this.edital.setLembretes(new LinkedList<>());
    }
    

// Getters e Setters
    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public Boolean getIsEditando() {
        return isEditando;
    }

    public void setIsEditando(Boolean isEditando) {
        this.isEditando = isEditando;
    }
    
    

// Ações
    
    public void editar() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String parametroId = params.get("id");
        
        if (parametroId != null && !parametroId.isEmpty()) {
            Long id = Long.parseLong(parametroId);
            
            EditalDAO dao = new EditalDAO();
            
            Edital editalEditando = dao.findById(id);
        
            if (editalEditando != null) {
                this.edital = editalEditando;
                this.isEditando = Boolean.TRUE;
            } else {
                this.isEditando = Boolean.FALSE;
            }
        } else {
            this.isEditando = Boolean.FALSE;
        }
        
    }        
    
    public String salvar() {
        EditalDAO dao = new EditalDAO();        
        dao.save(this.edital);
        
        return "editais?faces-redirect=true";
    }
    
    public void adicionarLembrete() {
        this.edital.getLembretes().add(new Lembrete());
    }
    
    public void removerLembrete(Lembrete lembrete) {
        this.edital.getLembretes().remove(lembrete);
    }
    
}
