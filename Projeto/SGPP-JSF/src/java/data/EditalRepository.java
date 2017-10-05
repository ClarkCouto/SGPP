package data;

// Singleton criado para simulação de persistência de dados.

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import models.Edital;

public class EditalRepository {
    
    private static EditalRepository instance;
    private Collection<Edital> editais;
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
        e.setIdEdital(numeroEditais++);
        e.setDataCadastro(LocalDateTime.now((ZoneId.systemDefault())));
        e.setDataUltimaAlteracao(e.getDataCadastro());
        return editais.add(e);
    }
    
    public Boolean update(Edital e) {
        if (!removeById(e.getIdEdital())) {
            return false;
        }
        
        return add(e);
    }
    
    public Edital findById(Long id) {
        return editais.stream().filter(e -> e.getIdEdital().equals(id)).findFirst().orElse(null);
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
