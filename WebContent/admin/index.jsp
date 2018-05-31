<!-- 当访问地址
http://127.0.0.1:8080/tmall_ssh/admin 时自动跳转到 
http://127.0.0.1:8080/tmall_ssh/admin_category_list -->
<%
	response.sendRedirect("../admin_category_list");
%>