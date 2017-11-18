package entities;

import entities.Coordenador;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-17T12:59:27")
@StaticMetamodel(GrupoDePesquisa.class)
public class GrupoDePesquisa_ extends BaseEntityAudit_ {

    public static volatile SingularAttribute<GrupoDePesquisa, String> nome;
    public static volatile SingularAttribute<GrupoDePesquisa, Coordenador> coordenador;

}