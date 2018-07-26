package pers.anshay.tmall.interceptor;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import pers.anshay.tmall.pojo.OrderItem;
import pers.anshay.tmall.pojo.User;
import pers.anshay.tmall.service.OrderItemService;

/**
 * @author Anshay
 * @date 2018年7月26日
 * @explain 拦截器-购物总数
 */
public class CartTotalItemNumberInterceptor extends AbstractInterceptor {
	@Autowired
	OrderItemService orderItemService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext ctx = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
		ServletContext servletContext = (ServletContext) ctx.get(StrutsStatics.SERVLET_CONTEXT);

		String contextPath = servletContext.getContextPath();
		String uri = request.getRequestURI();
		uri = StringUtils.remove(uri, contextPath);
		if (uri.startsWith("/fore")) {
			User user = (User) ActionContext.getContext().getSession().get("user");
			if (null != user) {
				int cartTotalItemNumber = 0;
				if (null != user) {
					List<OrderItem> ois = orderItemService.list("user", user, "order", null);
					for (OrderItem oi : ois)
						cartTotalItemNumber += oi.getNumber();
				}
				ctx.getSession().put("cartTotalItemNumber", cartTotalItemNumber);
			} else {
				ctx.getSession().put("cartTotalItemNumber", 0);
			}
		}
		return invocation.invoke();
	}
}
