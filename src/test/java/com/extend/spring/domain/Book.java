
package com.extend.spring.domain;

import static javax.persistence.FetchType.LAZY;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 * 
 * @author 星志
 *
 */
@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)-->默认继承策略,只生成一张表,使用type字段表示子类类型,一般为类名
//@Inheritance(strategy = InheritanceType.JOINED)-->子类无主键,只有子类独有的字段和一个指向父类的外键.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)//子类是一张单独的表
//与父类没有任何主外键关系,查询父类时使用union联合查询
@NamedEntityGraph(name = "Book.fetch.category.and.author",//自定义名字
attributeNodes = { @NamedAttributeNode("category") })//表示在抓取Book的时候,同时抓取category
public class Book extends DomainImpl {

	private String name;

	@ManyToOne
	/*
	 * optional default true //表示外键是否可以为空
	 * fetch() default LAZY; //默认抓取策略
	 * cascade() default {}; //级联操作
	 */
	private Category category;

	@OneToMany(mappedBy = "book" )
	private List<BookAuthor> authors;
	
	@Version
	private int version;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<BookAuthor> getAuthors() {
		return authors;
	}

	public void setAuthors(List<BookAuthor> authors) {
		this.authors = authors;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
