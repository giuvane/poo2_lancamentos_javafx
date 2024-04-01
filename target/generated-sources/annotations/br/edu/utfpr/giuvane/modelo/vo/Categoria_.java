package br.edu.utfpr.giuvane.modelo.vo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Categoria.class)
public abstract class Categoria_ {

	public static volatile SingularAttribute<Categoria, Long> codigo;
	public static volatile ListAttribute<Categoria, String> subCategorias;
	public static volatile ListAttribute<Categoria, Lancamento> lancamentos;
	public static volatile SingularAttribute<Categoria, String> nome;

}

