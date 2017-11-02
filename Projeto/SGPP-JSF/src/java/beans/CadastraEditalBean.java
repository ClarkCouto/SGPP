package beans;

import dao.EditalDAO;
import data.EditalRepository;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import entities.Edital;
import entities.Lembrete;
import java.util.LinkedList;

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
            
            EditalDAO dao = new EditalDAO();
            
            Edital editalEditando = dao.findById(id);
        
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
    
    public void adicionarLembrete() {
        if (edital.getLembretes() == null)
            edital.setLembretes(new LinkedList<>());
        edital.getLembretes().add(new Lembrete());
    }
    
    public String salvar() {
        
        EditalDAO dao = new EditalDAO();        
        dao.save(this.edital);        
        
        return "listarEditais?faces-redirect=true";
    }
    
}
