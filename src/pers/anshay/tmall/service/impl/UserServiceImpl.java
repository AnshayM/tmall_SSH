package pers.anshay.tmall.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import pers.anshay.tmall.pojo.User;
import pers.anshay.tmall.service.UserService;

/**
 * @author Anshay
 * @date 2018年7月17日
 * @explain 用户管理
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Override
	public boolean isExist(String name) {
		List l = list("name", name);
		if (!l.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public User get(String name, String password) {
		List<User> l = list("name", name, "password", password);
		if (l.isEmpty()) {
			return null;
		}
		return l.get(0);
	}

}
