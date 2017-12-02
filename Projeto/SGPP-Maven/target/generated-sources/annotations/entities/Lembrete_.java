package entities;

import entities.Documento;
import entities.TipoDocumento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-02T21:15:56")
@StaticMetamodel(Lembrete.class)
public class Lembrete_ extends BaseEntityAudit_ {

    public static volatile SingularAttribute<Lembrete, TipoDocumento> tipoDocumento;
    public static volatile SingularAttribute<Lembrete, Boolean> obrigatorio;
    public static volatile SingularAttribute<Lembrete, Documento> documento;
    public static volatile SingularAttribute<Lembrete, Date> dataLimite;
    public static volatile SingularAttribute<Lembrete, String> descricao;

}