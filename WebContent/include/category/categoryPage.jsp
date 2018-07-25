<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<title>模仿天猫官网-${category.name}</title>
<div id="category">
	<div class="categoryPageDiv">
		<!-- 显示当前分类图片 -->
		<img src="img/category/${category.id}.jpg">
		<!-- 排序条 -->
		<%@include file="sortBar.jsp"%>
		<!-- 产品列表 -->
		<%@include file="productsByCategory.jsp"%>

	</div>

</div>