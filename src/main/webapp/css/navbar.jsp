<%--
  Created by IntelliJ IDEA.
  User: Ulugbek
  Date: 6/15/2023
  Time: 4:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navbar</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylee.css">
</head>
<body>
<div class="container-fluid"style="height: 5px; background-color: black"></div>

<div class="container-fluid p-3">
    <div class="row">
        <div class="col-md-3">
            <h1 class="page-title">Welcome to tour agency</h1>
        </div>

        <div class="col-md-6" style="margin-left: 370px">
            <form class="form-inline my-2 my-lg-0" style="float: right">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><i
                        class="fa-solid fa-magnifying-glass"></i> Search
                </button>
            </form>
        </div>

<%--        <div class="col-md-3">--%>
<%--            <a href="" class="btn btn-success"><i class="fa-solid fa-right-to-bracket"></i> Login</a>--%>
<%--            <a href="" class="btn btn-primary text-white"><i class="fa-solid fa-user-plus"></i> Register</a>--%>
<%--        </div>--%>
    </div>
</div>

<nav class="navbar navbar-expand-lg navbar-dark bg-custom">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#"><i class="fa-solid fa-house"></i> Home <span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="#"><i class="fa-solid fa-map-location-dot"></i> Upcoming Tours <span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="#"><i class="fa-solid fa-passport"></i> Passed Tours <span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <a href="" class="btn btn-success"><i class="fa-solid fa-right-to-bracket"></i> Login</a>
            <a href="" class="btn btn-primary text-white" style="margin-left: 5px"><i class="fa-solid fa-user-plus"></i> Register</a>
<%--            <button class="btn btn-light my-2 my-sm-0" type="submit"><i class="fa-solid fa-user-gear"></i> Settings--%>
<%--            </button>--%>
<%--            <button class="btn btn-light my-2 my-sm-0 ml-1" type="submit"><i--%>
<%--                    class="fa-solid fa-arrow-right-from-bracket"></i> Logout--%>
<%--            </button>--%>
        </form>
    </div>
</nav>
</body>
</html>
