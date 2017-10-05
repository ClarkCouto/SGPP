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
        return EditalRepository.getInstance().getEditais();
    }
    
    public Boolean removeById(Long id) {
        return EditalRepository.getInstance().removeById(id);
    }
    
}
