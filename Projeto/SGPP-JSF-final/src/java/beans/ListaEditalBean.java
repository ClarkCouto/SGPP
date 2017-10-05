package beans;

import data.EditalRepository;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.Edital;

@ManagedBean
@RequestScoped
public class ListaEditalBean {
    
    public Collection<Edital> getEditais() {
        Collection<Edital> editais = EditalRepository.getInstance().getEditais();
        
        System.out.println(editais);
        
        return editais;
    }
    
    public String removeById(Long id) {
        EditalRepository.getInstance().removeById(id);
        
        return "listarEditais";
    }
    
//    public String editById(Long id) {
//        return "";
//    }
    
}
