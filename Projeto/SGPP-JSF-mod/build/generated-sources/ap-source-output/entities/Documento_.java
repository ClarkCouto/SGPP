package entities;

import entities.TipoDocumento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-19T14:38:02")
@StaticMetamodel(Documento.class)
public class Documento_ extends BaseEntityAudit_ {

    public static volatile SingularAttribute<Documento, TipoDocumento> tipoDocumento;
    public static volatile SingularAttribute<Documento, Boolean> entregue;
    public static volatile SingularAttribute<Documento, String> arquivoAnexo;
    public static volatile SingularAttribute<Documento, Date> dataEntregue;

}