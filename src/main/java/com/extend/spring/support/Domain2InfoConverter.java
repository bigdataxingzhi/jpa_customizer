
package com.extend.spring.support;

import org.springframework.core.convert.converter.Converter;

/**
 * 
 * @author 星志
 *
 * @param <T>
 * @param <I>
 */
public interface Domain2InfoConverter<T, I> extends Converter<T, I> {
	
}
