package entities;

import entities.Bolsa;
import entities.Curso;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-13T20:22:57")
@StaticMetamodel(Aluno.class)
public class Aluno_ extends Pessoa_ {

    public static volatile SingularAttribute<Aluno, Boolean> bolsista;
    public static volatile SingularAttribute<Aluno, Bolsa> bolsa;
    public static volatile SingularAttribute<Aluno, Curso> curso;

}