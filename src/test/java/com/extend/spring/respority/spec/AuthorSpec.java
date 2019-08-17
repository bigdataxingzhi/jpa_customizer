package com.extend.spring.respority.spec;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import com.extend.spring.domain.Author;
import com.extend.spring.dto.AuthorCondition;
import com.extend.spring.repository.spec.support.QueryWraper;
import com.extend.spring.repository.spec.support.ShopSimpleSpecification;

/**
 * 使用案例
 * @author 星志
 *
 */
public class AuthorSpec extends ShopSimpleSpecification<Author, AuthorCondition> {

	public AuthorSpec(AuthorCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Author> queryWraper) {
//		addLikeCondition(queryWraper, "name");
		
		if(StringUtils.isNotBlank(getCondition().getName())) {
			Predicate nameLike = createLikeCondition(queryWraper, "name", getCondition().getName());
			Predicate emailLike = createLikeCondition(queryWraper, "email", getCondition().getName());
			queryWraper.addPredicate(queryWraper.getCb().or(nameLike, emailLike));
		}
		
		addBetweenCondition(queryWraper, "age");
		addEqualsCondition(queryWraper, "sex");
		
		addEqualsConditionToColumn(queryWraper, "enable", true);
	}
	
	@Override
	protected void addFetch(Root<Author> root) {
		root.fetch("books", JoinType.LEFT);
	}
	
	

}
