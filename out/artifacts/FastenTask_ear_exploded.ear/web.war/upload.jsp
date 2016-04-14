<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Загрузить</title>
    </head>
    <body>
        <jsp:include page="topMenu.jsp"/>
        <h1>Загрузить xml</h1>
        <%
            String result = (String)request.getAttribute("result");
            if(result != null){
        %>
            <%=result%>
        <%
            }
        %>
        <form action="upload" enctype="multipart/form-data" method="post">
            <p>
                <input type="file" name="file" accept="text/xml">
                <input type="submit" value="Отправить">
            </p>
        </form>
    </body>
</html>
