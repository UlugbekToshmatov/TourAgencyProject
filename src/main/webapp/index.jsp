<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tour Agency</title>
    <%@include file="css/allCSS.jsp"%>
    <%@include file="html/navbar.jsp"%>
</head>
<body>
<h1>Hello World!
</h1>
<br/>
<a href="html/admin/add_tour.jsp">Add Tour</a><br>
<a href="view-all-tours">View All Tours</a><br>
<a href="hello-servlet">View All Tour Images</a><br>
<form action="hello-servlet">
    <input type="text" name="name" value="Enter your name">
    <input type="submit" value="Submit">
</form>
</body>
</html>