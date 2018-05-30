package pers.anshay.tmall.test;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.anshay.tmall.dao.impl.DAOImpl;
import pers.anshay.tmall.pojo.Category;

/**
 * @author Anshay
 * @date 2018年5月29日
 * @explain 使用JUnit Test的方式运行TestTmall 类，其作用是当数据库中没有分类数据的时候，添加两条数据
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestTmall {
	@Autowired
	DAOImpl dao;

	@Test
	public void delete() {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		List<Category> cs = dao.findByCriteria(dc);
		for (Category c : cs) {
			dao.delete(c);
		}
	}

	@Test
	public void test() {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		List<Category> cs = dao.findByCriteria(dc);
		if (cs.isEmpty()) {
			for (int i = 0; i < 10; i++) {
				Category c = new Category();
				c.setName("测试类" + (i + 1));
				dao.save(c);
			}
			System.out.println("成功添加10个测试分类");

		}
	}
}
