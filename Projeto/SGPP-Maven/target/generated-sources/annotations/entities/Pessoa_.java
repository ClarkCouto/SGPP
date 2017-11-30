package entities;

import entities.Instituicao;
import entities.Pessoa.Sexo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-29T23:29:54")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ extends BaseEntityAudit_ {

    public static volatile SingularAttribute<Pessoa, String> telefoneFixo;
    public static volatile SingularAttribute<Pessoa, String> cpf;
    public static volatile SingularAttribute<Pessoa, Instituicao> instituicao;
    public static volatile SingularAttribute<Pessoa, String> nome;
    public static volatile SingularAttribute<Pessoa, String> telefoneCelular;
    public static volatile SingularAttribute<Pessoa, Date> dataNascimento;
    public static volatile SingularAttribute<Pessoa, Sexo> sexo;
    public static volatile SingularAttribute<Pessoa, String> email;

}