<%@ page import="com.example.touragency.beans.Tour" %><%--
  Created by IntelliJ IDEA.
  User: Ulugbek
  Date: 6/27/2023
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update tour</title>
</head>
<body>
    <%
        Tour tour = (Tour) request.getAttribute("tour");
    %>
    <div class="container">
        <div class="row">
            <div class="col-md-3 offset-md-4">
                <div class="card">
                    <div class="card-body">
                        <h3 class="text-center">Update tour</h3>
                        <form action="update" method="post"
                              enctype="multipart/form-data">
                            <div class="form-group">
                                <input name="id" type="hidden" value="<%=tour.getId()%>" class="form-control"
                                       id="tour_id" aria-describedby="Admin">
                            </div>

                            <div class="form-group">
                                <label for="tour_title">Tour Title</label>
                                <input name="title" type="text" value="<%=tour.getTitle()%>" class="form-control"
                                       id="tour_title" aria-describedby="Admin">
                            </div>

                            <div class="form-group">
                                <label for="tour_description">Tour Description</label>
                                <input name="description" type="text" value="<%=tour.getDescription()%>" class="form-control"
                                       id="tour_description" aria-describedby="Admin">
                            </div>

                            <div class="form-group">
                                <label for="tour_venue">Tour Venue</label>
                                <input name="venue" type="text" value="<%=tour.getVenue()%>" class="form-control"
                                       id="tour_venue" aria-describedby="Admin">
                            </div>

                            <div class="form-group">
                                <label for="tour_price">Tour Price</label>
                                <input name="price" type="text" value="<%=tour.getPrice()%>" class="form-control"
                                       id="tour_price" aria-describedby="Admin">
                            </div>

                            <div class="form-group">
                                <label for="image_path">Tour Image</label>
                                <input name="image" type="file" accept="image/jpeg, image/png" class="form-control"
                                       value="<%=tour.getOverviewImagePath()%>" id="image_path" aria-describedby="Admin">
                            </div>

                            <button type="submit" class="btn btn-primary">Update</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
