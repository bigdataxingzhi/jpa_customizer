
package com.extend.spring.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * @author 星志
 *自定义的Repository接口,使用@NoRepositoryBean注解,说明了spring不为该接口生成代理
 *①继承了JpaRepository,可以使用分页和排序,还可以使用简单的example查询
 *②继承了JpaSpecificationExecutor,可使用动态查询.
 * @param <T>
 */
@NoRepositoryBean
public interface BookShopRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
