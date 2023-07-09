<%--
  Created by IntelliJ IDEA.
  User: Ulugbek
  Date: 6/14/2023
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>Admin: add tour</title>
    <%@include file="../../css/allCSS.jsp"%>
</head>
<a href="../../index.jsp">Home</a>
<body>
    <%@include file="../navbar.jsp"%>
    <div class="container">
        <div class="row">
            <div class="col-md-3 offset-md-4">
                <div class="card">
                    <div class="card-body">
                        <h3 class="text-center">Add tour</h3>
                        <form action="${pageContext.request.contextPath}/tour" method="post"
                        enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="tour_title">Tour Title</label>
                                <input name="title" type="text" class="form-control"
                                       id="tour_title" aria-describedby="Admin">
                            </div>

                            <div class="form-group">
                                <label for="tour_description">Tour Description</label>
                                <input name="description" type="text" class="form-control"
                                       id="tour_description" aria-describedby="Admin">
                            </div>

                            <div class="form-group">
                                <label for="tour_venue">Tour Venue</label>
                                <input name="venue" type="text" class="form-control"
                                       id="tour_venue" aria-describedby="Admin">
                            </div>

                            <div class="form-group">
                                <label for="tour_price">Tour Price</label>
                                <input name="price" type="text" class="form-control"
                                       id="tour_price" aria-describedby="Admin">
                            </div>

                            <div class="form-group">
                                <label for="image_path">Tour Image</label>
                                <input name="image" type="file" accept="image/jpeg, image/png" class="form-control"
                                       id="image_path" aria-describedby="Admin">
                            </div>

                            <div class="form-group">
                                <label for="tour_date">Tour Date</label>
                                <input name="date" type="date" class="form-control"
                                       id="tour_date" aria-describedby="Admin">
                            </div>

                            <div class="form-group">
                                <label for="tour_capacity">Tour Capacity</label>
                                <input name="capacity" type="number" min="4" class="form-control"
                                       id="tour_capacity" aria-describedby="Admin">
                            </div>

                            <button type="submit" class="btn btn-primary">Add</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
