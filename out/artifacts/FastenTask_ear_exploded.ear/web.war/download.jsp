
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Скачать</title>
    </head>
    <body>
        <jsp:include page="topMenu.jsp"/>
        <a href="<%=request.getContextPath()%>/download?file=catalog.xml">catalog.xml</a>
    </body>
</html>
