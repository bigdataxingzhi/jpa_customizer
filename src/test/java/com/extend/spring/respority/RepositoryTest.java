
package com.extend.spring.respority;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.extend.spring.BaseTest;
import com.extend.spring.domain.Author;
import com.extend.spring.domain.Book;
import com.extend.spring.domain.Ebook;
import com.extend.spring.domain.PrintBook;
import com.extend.spring.dto.AuthorCondition;
import com.extend.spring.dto.Sex;
import com.extend.spring.respority.spec.AuthorSpec;


/**
 * 
 * @author 星志
 *
 */
public class RepositoryTest extends BaseTest {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private PrintBookRepository printBookRepository;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	
	@Test
	public void testSpecQuery1() {
		
		AuthorCondition condition = new AuthorCondition();
		condition.setName("test");
		authorRepository.findAll(new AuthorSpec(condition));
		
	}
	
	@Test
	public void testSpecQuery2() {
		
		AuthorCondition condition = new AuthorCondition();
		condition.setName("test");
		condition.setAge(18);
		authorRepository.findAll(new AuthorSpec(condition));
		
	}
	
	@Test
	public void testSpecQuery3() {
		
		AuthorCondition condition = new AuthorCondition();
		condition.setName("test");
		condition.setAge(18);
		condition.setAgeTo(60);
		condition.setSex(Sex.MAN);
		authorRepository.findAll(new AuthorSpec(condition));
		
	}
	
	
	
	
	
	@Test
	public void test1(){
		 //在关闭测试类上的事务注解,使用手动开启事务,手动提交,才会发出更新语句
		//原因是:测试时事务会回滚,不会com梅米特事务.因此不会发出update语句
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		Book book = bookRepository.findOne(1L);		
		book.setName("美女与野兽");
		//该方法可以手动刷新缓存,不用等待commit.
		bookRepository.saveAndFlush(book);;
		System.out.println("success");
		transactionManager.commit(status);
		
	}
	
	@Test
	public void test6() {
		Author author = new Author();
		author.setEmail("xxx");
		authorRepository.saveAndFlush(author);
	}
	
	@Test
	public void test5() {
		Book book = bookRepository.findOne(1L);	
		book.setName("xxx");
		bookRepository.saveAndFlush(book);
	}
	
	@Test
	public void test2(){
		//通过该测试,我们发现,findAll()方法不会使用一级缓存
		bookRepository.findAll();
		bookRepository.findAll();
	}
	
	@Test
	public void test3(){
//		Book book = bookRepository.findOne(1L);	
//		System.out.println(book.getCategory().getName());
		
		//通过该测试,我们方法在使用自定义的接口查询是@ManyToOne注解没有生效
		//解决方法:
		//Book类上使用@NamedEntityGraph注解...
		Book book = bookRepository.findByName("战争与和平");
		System.out.println(book.getCategory().getName());
	}
	
	@Test
	public void test4(){
	
		PrintBook printBook = new PrintBook();
		printBook.setName("1");
		bookRepository.save(printBook);
		
		Ebook ebook = new Ebook();
		ebook.setName("2");
		bookRepository.save(ebook);
		
		List<Book> books = bookRepository.findAll();
		books.stream().forEach(book -> System.out.println(book.getClass().getSimpleName()));
		
		List<PrintBook> pbooks = printBookRepository.findAll();
		pbooks.stream().forEach(book -> System.out.println(book.getClass().getSimpleName()));
		
	}
	
}
