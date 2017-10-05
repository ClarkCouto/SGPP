package beans;

import data.EditalRepository;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.Edital;

@ManagedBean
@ViewScoped
public class CadastraEditalBean {
    
    private Edital edital = new Edital();
    private Boolean isEditando;


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
            
            Edital editalEditando = EditalRepository.getInstance().findById(id);
        
            if (editalEditando != null) {
                this.edital = editalEditando;
                this.isEditando = Boolean.TRUE;
                System.out.println("Teste");
            } else {
                this.isEditando = Boolean.FALSE;
            }
        } else {
            this.isEditando = Boolean.FALSE;
        }
        
    }
    
    public String salvar() {
        
        if (isEditando != null && isEditando) {
            EditalRepository.getInstance().update(edital);
        } else {
            Boolean editalAdded = EditalRepository.getInstance().add(edital);
            if (!editalAdded) {
                System.out.println("Erro ao adicionar usuário.\n");
            }
        }
        EditalRepository.getInstance().list();
        
        return "listarEditais?faces-redirect=true";
    }
    
}
