package com.extend.spring.respority.spec;

import com.extend.spring.domain.Book;
import com.extend.spring.dto.BookCondition;
import com.extend.spring.repository.spec.support.QueryWraper;
import com.extend.spring.repository.spec.support.ShopSimpleSpecification;

/**
 * 使用案例
 * @author 星志
 *
 */
public class BookSpec extends ShopSimpleSpecification<Book, BookCondition> {

	public BookSpec(BookCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Book> queryWraper) {
		addLikeCondition(queryWraper, "name");
	}

}
