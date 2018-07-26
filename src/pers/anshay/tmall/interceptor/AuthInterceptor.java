package pers.anshay.tmall.interceptor;

import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import pers.anshay.tmall.pojo.User;
import pers.anshay.tmall.service.OrderItemService;

/**
 * @author Anshay
 * @date 2018年7月26日
 * @explain 过滤器判断如果不是注册，登录，产品这些，就进行登录校验
 * 
 *          1. 准备字符串数组 noNeedAuthPage，存放哪些不需要登录也能访问的路径 ; 2. 获取contextPath：
 *          tmall_ssh 2. 获取uri; 3. 去掉前缀/tmall_ssh 4. 如果访问的地址是/fore开头 4.1
 *          取出fore后面的字符串，比如是forecart,那么就取出cart 4.2 判断cart是否是在noNeedAuthPage 4.2
 *          如果不在，那么就需要进行是否登录验证 4.3 从session中取出"user"对象 4.4
 *          如果user对象不存在，就客户端跳转到login.jsp 4.5 否则就正常执行
 */
public class AuthInterceptor extends AbstractInterceptor {

	@Autowired
	OrderItemService orderItemService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String[] noNeedAuthPage = new String[] { "home", "checkLogin", "register", "loginAjax", "login", "product",
				"category", "search" };

		ActionContext ctx = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ctx.get(StrutsStatics.HTTP_RESPONSE);
		ServletContext servletContext = (ServletContext) ctx.get(StrutsStatics.SERVLET_CONTEXT);
		String contextPath = servletContext.getContextPath();
		String uri = request.getRequestURI();
		uri = StringUtils.remove(uri, contextPath);
		if (uri.startsWith("/fore")) {
			String method = StringUtils.substringAfterLast(uri, "/fore");
			if (!Arrays.asList(noNeedAuthPage).contains(method)) {
				User user = (User) ctx.getSession().get("user");
				if (null == user) {
					response.sendRedirect("login.jsp");
					return null;
				}
			}
		}
		return invocation.invoke();

	}

}
