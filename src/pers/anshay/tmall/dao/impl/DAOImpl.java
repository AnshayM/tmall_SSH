package pers.anshay.tmall.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Anshay
 * @date 2018年5月29日
 * @explain DAOImpl 继承了HibernateTemplate，从而提供了各种各样的CRUD方法，满足各种数据库操作的需要。
 */

@Repository("dao")
public class DAOImpl extends HibernateTemplate {
	/*
	 * 提供setSessionFactory()方法， 以用于注入SessionFactory
	 */
	@Resource(name = "sf")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}
