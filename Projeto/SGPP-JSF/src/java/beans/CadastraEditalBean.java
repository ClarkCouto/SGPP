package beans;

import data.EditalRepository;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import models.Edital;

@ManagedBean
@RequestScoped
public class CadastraEditalBean {
    
    private Edital edital = new Edital();
    private Boolean isEditando = false;
    
// Getters e Setters

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

// Ações
    
    public void editar() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String parametroId = params.get("id");
        Long id = Long.getLong(parametroId);
        
        Edital editalEditando = EditalRepository.getInstance().findById(id);
        
        if (editalEditando != null) {
            this.edital = editalEditando;
            this.isEditando = true;
        }
        
    }
    
    public String salvar() {
        
        if (isEditando) {
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
