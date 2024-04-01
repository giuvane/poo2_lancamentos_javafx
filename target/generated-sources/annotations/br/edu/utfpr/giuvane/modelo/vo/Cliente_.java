package br.edu.utfpr.giuvane.modelo.vo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ extends br.edu.utfpr.giuvane.modelo.vo.Pessoa_ {

	public static volatile SingularAttribute<Cliente, Boolean> ativo;
	public static volatile ListAttribute<Cliente, Lancamento> lancamentos;
	public static volatile SingularAttribute<Cliente, Endereco> endereco;
	public static volatile SingularAttribute<Cliente, String> cpf;
	public static volatile SingularAttribute<Cliente, String> nome;

}

