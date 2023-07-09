<%@ page import="com.example.touragency.beans.Tour" %>
<%@ page import="com.example.touragency.beans.TourImage" %>
<%@ page import="java.util.LinkedHashSet" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: Ulugbek
  Date: 6/26/2023
  Time: 10:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View tour</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<a href="index.jsp">Home</a><br><br>
<div style="text-align: center">
<h2>Tour details</h2>
<%Tour tour = (Tour) request.getAttribute("tour");%>
<%=tour.getId()%><br>
<%=tour.getTitle()%><br>
<%=tour.getDescription()%><br>
<%=tour.getVenue()%><br>
<%=tour.getPrice()%><br><br>
</div>
<div style="text-align: center">
    <%
        Set<TourImage> imageSet = (LinkedHashSet<TourImage>) request.getAttribute("tour_images");
        for (TourImage image : imageSet) {
    %>
    <div class="images">
        <a target="_self" href="images/<%=image.getImagePath()%>">
            <img src="images/<%=image.getImagePath()%>" height="200" alt="imgs">
        </a>
        <div>
            <a href="delete-image?tour_id=<%=tour.getId()%>&image_id=<%=image.getId()%>">Delete</a><br>
            <a href="set-tour-image?tour_id=<%=tour.getId()%>&image_path=<%=image.getImagePath()%>">Set this image as overview image</a>
        </div>
    </div>
    <%
        }
    %>
    <br>
</div><br><br>
<a href="delete?tour_id=<%=tour.getId()%>">Delete this tour</a><br>
<a href="update?tour_id=<%=tour.getId()%>">Update this tour</a><br>
<a href="html/admin/image/add_image.jsp?tour_id=<%=tour.getId()%>">Add image to this tour</a><br>
</body>
</html>
