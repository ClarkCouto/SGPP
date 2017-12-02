package entities;

import entities.CategoriaBolsa;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-02T00:54:21")
@StaticMetamodel(Bolsa.class)
public class Bolsa_ extends BaseEntityAudit_ {

    public static volatile SingularAttribute<Bolsa, CategoriaBolsa> categoriaBolsa;
    public static volatile SingularAttribute<Bolsa, String> nome;
    public static volatile SingularAttribute<Bolsa, Integer> quantidade;

}