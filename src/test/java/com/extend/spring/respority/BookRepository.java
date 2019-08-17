
package com.extend.spring.respority;

import org.springframework.data.jpa.repository.EntityGraph;

import com.extend.spring.domain.Book;
import com.extend.spring.support.BookShopRepository;



public interface BookRepository extends BookShopRepository<Book> {
	//方法一:
//	@EntityGraph(attributePaths={"category"})
//	此时不需要在Book类上使用@NamedEntityGraph注解
	@EntityGraph("Book.fetch.category.and.author")//方法二:需要在Book类上使用@NamedEntityGraph注解
	Book findByName(String bookname);
	
}
