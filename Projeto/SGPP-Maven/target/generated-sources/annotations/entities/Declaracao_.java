package entities;

import entities.Coordenador;
import entities.Projeto;
import entities.TextoBaseDeclaracao;
import entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-02T00:54:21")
@StaticMetamodel(Declaracao.class)
public class Declaracao_ extends BaseEntityAudit_ {

    public static volatile SingularAttribute<Declaracao, Projeto> projeto;
    public static volatile SingularAttribute<Declaracao, TextoBaseDeclaracao> textoBaseDeclaracao;
    public static volatile SingularAttribute<Declaracao, Date> dataEmissao;
    public static volatile SingularAttribute<Declaracao, Coordenador> responsavel;
    public static volatile SingularAttribute<Declaracao, Usuario> destinatario;

}