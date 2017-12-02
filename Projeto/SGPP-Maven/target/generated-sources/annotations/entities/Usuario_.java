package entities;

import entities.Usuario.TipoUsuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-02T00:54:21")
@StaticMetamodel(Usuario.class)
public class Usuario_ extends Pessoa_ {

    public static volatile SingularAttribute<Usuario, String> senha;
    public static volatile SingularAttribute<Usuario, TipoUsuario> tipo;
    public static volatile SingularAttribute<Usuario, Date> ultimoAcesso;

}