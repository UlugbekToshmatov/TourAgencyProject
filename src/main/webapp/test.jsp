<%@ page import="com.example.touragency.beans.TourImage" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.LinkedHashSet" %><%--
  Created by IntelliJ IDEA.
  User: Ulugbek
  Date: 6/24/2023
  Time: 5:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Testing page</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<a href="index.jsp">Home</a><br>
<h3>All tour images</h3><br>
    <%
        Set<TourImage> imageSet = (LinkedHashSet<TourImage>) request.getAttribute("tour_images");
        for (TourImage image : imageSet) {
    %>
        <div class="images">
            <a target="_self" href="images/<%=image.getImagePath()%>">
                <img src="images/<%=image.getImagePath()%>" height="200" alt="img">
            </a>
        </div>
    <%
        }
    %>
</body>
</html>
