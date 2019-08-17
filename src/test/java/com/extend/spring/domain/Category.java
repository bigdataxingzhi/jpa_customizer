
package com.extend.spring.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
public class Category extends DomainImpl{
	
	@Column(length = 10, nullable = false, unique = true)
	private String name;
	
	@Transient
	private String xxxx;

	@OneToMany(mappedBy = "category")
	/*
	 * orphanRemoval() default false //表示当一个对象从集合中删除时,是否从数据库中移除.
	 * mappedBy
	 * fetch() default LAZY; //默认抓取策略
	 * cascade() default {}; //级联操作
	 */
	private List<Book> books;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXxxx() {
		return xxxx;
	}

	public void setXxxx(String xxxx) {
		this.xxxx = xxxx;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
