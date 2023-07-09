<%--
  Created by IntelliJ IDEA.
  User: Ulugbek
  Date: 7/2/2023
  Time: 12:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add image</title>
    <%@include file="../../../css/allCSS.jsp"%>
</head>
<body>
<a href="../../../index.jsp">Home</a>
<form action="${pageContext.request.contextPath}/tour-image?tour_id=<%=request.getParameter("tour_id")%>" method="post"
      enctype="multipart/form-data">
    <div style="text-align: center" class="form-group">
        <label for="image_path">Choose image</label><br>
        <input id="image_path" name="image" type="file" accept="image/jpeg, image/png" class="form-control"
               size="25px"><br>
        <button type="submit" class="btn btn-primary">Add</button>
    </div>
</form>
</body>
</html>
