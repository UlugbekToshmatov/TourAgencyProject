<%@ page import="java.util.List" %>
<%@ page import="com.example.touragency.beans.Tour" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.LinkedHashSet" %><%--
  Created by IntelliJ IDEA.
  User: Ulugbek
  Date: 6/17/2023
  Time: 7:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Testing Page</title>
</head>
<body>
<%--Welcome to tour agency Mr. <%=request.getAttribute("name")%><br>--%>
Servlet path: <%=request.getServletPath()%><br>
Current time: <%=LocalDateTime.now()%><br>
<a href="index.jsp">Home</a><br><br>
<h4>All Tours</h4>
<table class="border fa-solid">
    <tr class="text-center">
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Venue</th>
        <th>Price</th>
        <th>Image</th>
    </tr>
        <%
            Set<Tour> tours = (LinkedHashSet<Tour>) request.getAttribute("tours");
            if (tours != null) {
                for (Tour tour : tours) {
        %>
                <tr>
                    <td><%=tour.getId()%></td>
                    <td><%=tour.getTitle()%></td>
                    <td><%=tour.getDescription()%></td>
                    <td><%=tour.getVenue()%></td>
                    <td><%=tour.getPrice()%></td>
                    <td><a href="view-tour?tour_id=<%=tour.getId()%>">
                        <img src="images/<%=tour.getOverviewImagePath()%>" height="200" alt="img"></a>
                    </td>
                </tr>
        <%
                }
            }
        %>
</table>
</body>
</html>
