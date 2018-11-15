<%@ page language="java" contentType="application/msword; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//response.setContentType("application/msword");
response.setHeader("Content-disposition","attachment; filename="+new String("测试.doc".getBytes("GB2312"), "8859_1")); 
%>
<table border="1" bordercolor="red">
	<tr>
		<td>name</td>
		<td>age</td>
	</tr>
	<tr>
		<td><input type="text"/></td>
		<td>11</td>
	</tr>
</table>
<script>

</script>
</body>
</html>