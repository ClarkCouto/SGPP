package entities;

import entities.Aluno;
import entities.Colaborador;
import entities.Coordenador;
import entities.Edital;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-19T14:38:02")
@StaticMetamodel(Projeto.class)
public class Projeto_ extends BaseEntityAudit_ {

    public static volatile SingularAttribute<Projeto, Boolean> aipct;
    public static volatile SingularAttribute<Projeto, Integer> ano;
    public static volatile SingularAttribute<Projeto, Date> dataFim;
    public static volatile SingularAttribute<Projeto, Edital> edital;
    public static volatile SingularAttribute<Projeto, String> titulo;
    public static volatile CollectionAttribute<Projeto, Aluno> listaAlunos;
    public static volatile SingularAttribute<Projeto, Coordenador> coordenador;
    public static volatile SingularAttribute<Projeto, Date> dataInicio;
    public static volatile CollectionAttribute<Projeto, Colaborador> listaColaboradores;

}