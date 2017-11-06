package beans;

import dao.EditalDAO;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import entities.Edital;

@ManagedBean
@RequestScoped
public class ListaEditalBean {
    
    private String textoFiltro;    
    
    
    public Collection<Edital> getEditais() {
        
        EditalDAO dao = new EditalDAO();
        
        Collection<Edital> editais = dao.findAll();
        
        System.out.println(editais);
        
        return editais;
    }    
    
    public String removeById(Long id) {
        EditalDAO dao = new EditalDAO();
        
        dao.remove(id);
        
        return "editais";
    }
    
//    public String editById(Long id) {
//        return "";
//    }
    
}
