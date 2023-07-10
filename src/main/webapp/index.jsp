<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tour Agency</title>
    <link rel="stylesheet" href="css/stylee.css">
<%--  Google fonts  --%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fasthand&display=swap" rel="stylesheet">
    <%--    <style>--%>
    <%--        .background-img {--%>
    <%--            height: auto;--%>
    <%--            width: auto;--%>
    <%--            background-image: url("${pageContext.request.contextPath}/images/background_image.jpg");--%>
    <%--            background-size: cover;--%>
    <%--            background-repeat: no-repeat;--%>
    <%--        }--%>
    <%--    </style>--%>
</head>
<body>
<div class="container-fluid background-img">
    <%@include file="css/allCSS.jsp" %>
    <%@include file="css/navbar.jsp" %>
    <h1 class="text-center">Hello World!</h1>
    <br/>
    <a href="html/admin/add_tour.jsp"><i class="fa-regular fa-location-plus"></i> Add Tour</a><br>
    <a href="view-all-tours">View All Tours</a><br>
    <a href="hello-servlet">View All Tour Images</a><br>
    <form action="hello-servlet">
        <input type="text" name="name" value="Enter your name">
        <input type="submit" value="Submit">
    </form>

    <div class="container">
        <h3 class="text-center">Upcoming Tours</h3>
        <div class="row">
            <div class="col-md-3">
                <div class="card">
                    <div class="card-body text-center">
                        <img src="images/tashkent.jpg" alt="img here" style="height: 150px; width: 200px"
                        class="card-img-overlay">
                        <p>Tashkent tour</p>
                        <p>Trip along Tashkent city</p>
                        <p>Available seat left: 9</p>
                        <div class="row">
                            <a href="" class="btn btn-primary btn-sm"></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <br>
    <br>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate ea eligendi eos eveniet hic, incidunt ipsa
        minima, nostrum odit perferendis, quis soluta suscipit voluptatibus? Dicta dolor incidunt nulla quidem
        voluptates.</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate ea eligendi eos eveniet hic, incidunt ipsa
        minima, nostrum odit perferendis, quis soluta suscipit voluptatibus? Dicta dolor incidunt nulla quidem
        voluptates.</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate ea eligendi eos eveniet hic, incidunt ipsa
        minima, nostrum odit perferendis, quis soluta suscipit voluptatibus? Dicta dolor incidunt nulla quidem
        voluptates.</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate ea eligendi eos eveniet hic, incidunt ipsa
        minima, nostrum odit perferendis, quis soluta suscipit voluptatibus? Dicta dolor incidunt nulla quidem
        voluptates.</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate ea eligendi eos eveniet hic, incidunt ipsa
        minima, nostrum odit perferendis, quis soluta suscipit voluptatibus? Dicta dolor incidunt nulla quidem
        voluptates.</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate ea eligendi eos eveniet hic, incidunt ipsa
        minima, nostrum odit perferendis, quis soluta suscipit voluptatibus? Dicta dolor incidunt nulla quidem
        voluptates.</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate ea eligendi eos eveniet hic, incidunt ipsa
        minima, nostrum odit perferendis, quis soluta suscipit voluptatibus? Dicta dolor incidunt nulla quidem
        voluptates.</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate ea eligendi eos eveniet hic, incidunt ipsa
        minima, nostrum odit perferendis, quis soluta suscipit voluptatibus? Dicta dolor incidunt nulla quidem
        voluptates.</p>
</div>
</body>
</html>