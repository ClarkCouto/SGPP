package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-06T02:02:13")
@StaticMetamodel(BaseEntityAudit.class)
public class BaseEntityAudit_ extends BaseEntity_ {

    public static volatile SingularAttribute<BaseEntityAudit, String> alteradoPor;
    public static volatile SingularAttribute<BaseEntityAudit, String> criadoPor;
    public static volatile SingularAttribute<BaseEntityAudit, Date> dataCriacao;
    public static volatile SingularAttribute<BaseEntityAudit, Date> dataAlteracao;

}