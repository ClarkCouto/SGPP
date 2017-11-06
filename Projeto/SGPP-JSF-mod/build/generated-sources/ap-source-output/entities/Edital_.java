package entities;

import entities.Bolsa;
import entities.Lembrete;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-06T02:02:13")
@StaticMetamodel(Edital.class)
public class Edital_ extends BaseEntityAudit_ {

    public static volatile SingularAttribute<Edital, String> pdf;
    public static volatile SingularAttribute<Edital, String> numero;
    public static volatile SingularAttribute<Edital, String> origem;
    public static volatile SingularAttribute<Edital, String> titulo;
    public static volatile CollectionAttribute<Edital, Lembrete> lembretes;
    public static volatile CollectionAttribute<Edital, Bolsa> bolsas;

}