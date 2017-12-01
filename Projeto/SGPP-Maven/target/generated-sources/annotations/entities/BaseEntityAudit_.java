package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-30T21:52:02")
@StaticMetamodel(BaseEntityAudit.class)
public class BaseEntityAudit_ extends BaseEntity_ {

    public static volatile SingularAttribute<BaseEntityAudit, String> alteradoPor;
    public static volatile SingularAttribute<BaseEntityAudit, Date> dataUltimaAlteracao;
    public static volatile SingularAttribute<BaseEntityAudit, String> criadoPor;
    public static volatile SingularAttribute<BaseEntityAudit, Date> dataCriacao;

}