
package com.extend.spring.domain;

import javax.persistence.Entity;


@Entity
public class Ebook extends Book {
	
	private String format;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
}
