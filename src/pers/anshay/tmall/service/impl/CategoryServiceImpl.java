package pers.anshay.tmall.service.impl;

import org.springframework.stereotype.Service;

import pers.anshay.tmall.service.CategoryService;

/**
 * @author Anshay
 * @date 2018年5月29日
 * @explain 实现了CategoryService 接口，提供list()方法的具体实现。 同时自动装配(注入) 了DAOImpl的实例dao。
 *          在list()方法中，通过dao获取所有的分类对象。
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {

}
