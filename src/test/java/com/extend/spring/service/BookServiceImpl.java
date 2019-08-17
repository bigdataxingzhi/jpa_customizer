package com.extend.spring.service;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.extend.spring.domain.Book;
import com.extend.spring.dto.BookCondition;
import com.extend.spring.dto.BookInfo;
import com.extend.spring.respority.BookRepository;
import com.extend.spring.respority.spec.BookSpec;
import com.extend.spring.support.Domain2InfoConverter;
import com.extend.spring.support.QueryResultConverter;

@Service("bookService")
//@Transactional
public class BookServiceImpl {
	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	ModelMapper modelMapper;
	
	
	@Cacheable("books")
	public BookInfo getInfo(Long id) {
		Book book = bookRepository.findOne(id);
		BookInfo info = new BookInfo();
		BeanUtils.copyProperties(book, info);
		
		return info;
	}

	
	@Transactional
	public Page<BookInfo> query(BookCondition condition, Pageable pageable) {
		
		Page<Book> pageData = bookRepository.findAll(new BookSpec(condition), pageable);
		return QueryResultConverter.convert(pageData, pageable,new Domain2InfoConverter<Book, BookInfo>() {

			@Override
			public BookInfo convert(Book source) {
				return modelMapper.map(source, BookInfo.class);
			}
			
		});
	}

	



}
