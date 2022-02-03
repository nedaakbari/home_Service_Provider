<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/static/css/login.css"/>">

</head>
<body>
<div class="container">
    <input type="checkbox" id="flip">
    <div class="cover">
        <div class="front">
            <!--<img src="images/frontImg.jpg" alt="">-->
            <img src="<c:url value="/static/image/frontImg.jpg"/> ">
            <div class="text">
                <span class="text-1">We Are Glad To  <br> Having You</span>
                <span class="text-2">Trying to satisfy you</span>
            </div>
        </div>
        <div class="back">
            <!--<img class="backImg" src="images/backImg.jpg" alt="">-->
            <img src="<c:url value="/static/image/backImg.jpg"/> ">
            <div class="text">
                <span class="text-1">We Are Glad To  <br> Having You</span>
                <span class="text-2">Trying to satisfy you</span>
            </div>
        </div>
    </div>
    <div class="forms">
        <div class="form-content">
            <div class="login-form">
                <div class="title">Login</div>
                <p class="text-danger" style="text-color:red !important;">${error}</p>
                <form:form modelAttribute="expert" action="expertLogin" method="post">
                    <div class="input-boxes">
                        <div class="input-box">
                            <i class="fas fa-envelope"></i>
                            <form:input type="text" path="email" name="email" placeholder="Enter your email"/>
                            <form:errors path="email" cssClass="text-danger"/>
                        </div>
                        <div class="input-box">
                            <i class="fas fa-lock"></i>
                            <form:input type="password" path="password" name="password"
                                        placeholder="Enter your password"/>
                            <form:errors path="password" cssClass="text-danger !important"/>
                        </div>

                        <div class="button input-box">
                            <input type="submit" name="login" >
                        </div>
                        <div class="text sign-up-text">
                            Don't have an account? <label <%--for="flip"--%>>
                            <a href="<c:url value="/expertRegister"/>">Sigup now</a>
                        </label>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
</body>

</html>
