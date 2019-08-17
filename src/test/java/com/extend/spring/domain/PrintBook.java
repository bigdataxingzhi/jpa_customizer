
package com.extend.spring.domain;

import java.util.Date;

import javax.persistence.Entity;


@Entity
public class PrintBook extends Book {
	
	private Date printDate;

	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}
	
}
