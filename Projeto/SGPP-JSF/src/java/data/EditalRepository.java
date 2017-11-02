package data;

// Singleton criado para simulação de persistência de dados.

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import entities.Edital;

public class EditalRepository {
    
    private static EditalRepository instance;
    private ArrayList<Edital> editais;
    private Long numeroEditais = 0L;
    
    private EditalRepository() {
        editais = new ArrayList<>();
    }
    
    public static EditalRepository getInstance() {        
        if (instance == null) {
            instance = new EditalRepository();
        }        
        return instance;
    }
    
    public Boolean add(Edital e) {
        e.setId(numeroEditais++);        
        return editais.add(e);
    }
    
    public void update(Edital e) {
        int i;
        for(i = 0; i < editais.size(); i++) {
            if (editais.get(i).getId() == e.getId()) {
                editais.set(i, e);
            }
        }
    }
    
    public Edital findById(Long id) {
        return editais.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }
    
    public Boolean removeById(Long id) {
        return editais.remove(findById(id));
    }
    
    public void list() {
        editais.forEach(System.out::println);
    }
    
    public Collection<Edital> getEditais() {
        return editais;
    }
    
}
