
package com.extend.spring;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author 星志
 *测试方法基类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GenerateSpringDataJpaPredicateApplicationTests.class)
@Transactional
public class BaseTest {
	
}
