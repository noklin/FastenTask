<%@ page import="com.noklin.fastentask.data.entities.CdEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%String find = request.getParameter("find");
    String title = find == null ? "Список CD" : find;
%>
<html>
    <head>
        <title><%=title%></title>
    </head>
    <body>
        <jsp:include page="topMenu.jsp"/>
        <h1><%=title%></h1>
        <%
            if(find == null){
                ArrayList<CdEntity> list = (ArrayList<CdEntity>) request.getAttribute("data");

                for(CdEntity cd : list) {
                    String cdTitle = cd.getTitle();
        %>
                    <a href="<%=request.getContextPath()%>/list?find=<%=cdTitle%>"><%=cdTitle%></a><br>
        <%      }
            }else{
                CdEntity cd = (CdEntity) request.getAttribute("data");
                if(cd != null){
        %>
        <table border="1">
            <tr>
                <th>TITLE</th>
                <th>ARTIST</th>
                <th>COUNTRY</th>
                <th>COMPANY</th>
                <th>PRICE</th>
                <th>YEAR</th>
            </tr>
            <tr>
                <td><%=cd.getTitle()%></td>
                <td><%=cd.getArtist()%></td>
                <td><%=cd.getCountry()%></td>
                <td><%=cd.getCompany()%></td>
                <td><%=cd.getPrice()%></td>
                <td><%=cd.getYear()%></td>
            </tr>
        </table>
        <%      }else{%>
                <h5>Не найдено</h5>
        <%      }

            }%>

    </body>
</html>
