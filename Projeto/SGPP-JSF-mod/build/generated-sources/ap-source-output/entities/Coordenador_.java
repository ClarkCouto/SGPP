package entities;

import entities.Area;
import entities.GrupoDePesquisa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-06T02:02:13")
@StaticMetamodel(Coordenador.class)
public class Coordenador_ extends Usuario_ {

    public static volatile SingularAttribute<Coordenador, Area> area;
    public static volatile SingularAttribute<Coordenador, String> siape;
    public static volatile CollectionAttribute<Coordenador, GrupoDePesquisa> gruposDePesquisa;

}