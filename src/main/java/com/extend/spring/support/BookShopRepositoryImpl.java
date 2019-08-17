
package com.extend.spring.support;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author 星志
 *
 * @param <T>
 */
//在启动类上,配置spring-data使用BookShopRepositoryImpl来生成代理
//默认为SimpleJpaRepository
public class BookShopRepositoryImpl<T> extends SimpleJpaRepository<T, Long> {

	public BookShopRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}
	
	@Override
	@Transactional
	public <S extends T> S save(S entity) {
		System.out.println("保存了:"+entity.getClass().getSimpleName());
		return super.save(entity);
	}

}
