package entities;

import entities.Area;
import entities.GrupoDePesquisa;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-02T21:15:56")
@StaticMetamodel(Coordenador.class)
public class Coordenador_ extends Usuario_ {

    public static volatile SingularAttribute<Coordenador, Area> area;
    public static volatile SingularAttribute<Coordenador, String> siape;
    public static volatile ListAttribute<Coordenador, GrupoDePesquisa> gruposDePesquisa;

}