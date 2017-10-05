package data;

// Singleton criado para simulação de persistência de dados.

import java.util.ArrayList;
import java.util.Collection;
import models.Usuario;


public class UserRepository {
    
    private static UserRepository instance;
    private Collection<Usuario> usuarios;
    
    private UserRepository() {
        usuarios = new ArrayList<>();
        
        // Usuário para testes
        usuarios.add(new Usuario("111.111.111-11", "1234"));
        
    }    
    
    public static UserRepository getInstance() {
        
        if (instance == null) {
            instance = new UserRepository();
        }        
        return instance;
    }
    
    public Boolean add(Usuario u) {
        
        /* A senha não consta no formulário pois como o sistema
        * é de uso interno, acredito que o cadastro
        * precisaria ser aprovado internamente, então pensei
        * em gerar uma senha automaticamente caso o usuário
        * tivesse o cadastro aprovado (essa senha poderia
        * ser mudada no primeiro acesso ao sistema). */    
        u.changeSenha("1234", null);
        return usuarios.add(u);
    }
    
    public Usuario findByCpf(String cpf) {
        return usuarios.stream().filter(u -> u.getCpf().equals(cpf)).findFirst().orElse(null);
    }
    
    public void list() {
        usuarios.forEach(System.out::println);
    }
        
}
