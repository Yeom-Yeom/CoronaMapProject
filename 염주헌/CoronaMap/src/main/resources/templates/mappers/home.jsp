<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
<%--    <%@ include file="include/header.jsp" %>--%>
</head>
<body>
<%--<%@ include file="include/menu.jsp" %>--%>
<P> The time on the server is ${serverTime}.
</P> <%--// server타임은 생략해도 됨--%>
${result}
</body>
</html>