<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Register</title>  <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<c:url value="/static/css/login.css"/>">
</head>

<body>
<div class="container">
    <input type="checkbox" id="flip">
    <div class="cover">
        <div class="front">
            <img src="<c:url value="/static/image/frontImg.jpg"/> ">
            <div class="text">
                <span class="text-1">We Are Glad To  <br> Having You</span>
                <span class="text-2">Trying to satisfy you</span>
            </div>
        </div>
        <div class="back">
            <img src="<c:url value="/static/image/backImg.jpg"/> ">
            <div class="text">
                <span class="text-1">We Are Glad To  <br> Having You</span>
                <span class="text-2">Trying to satisfy you</span>
            </div>
        </div>
    </div>
    <div class="forms">
        <div class="form-content">
            <div class="signup-form">
                <div class="title">Signup</div>
                <form:form modelAttribute="expert" enctype="multipart/form-data" action="expertRegister"
                           method="post" id="form">
                    <div class="input-boxes">
                        <div>
                            MALE <form:radiobutton path="gender" value="MALE"/>
                            FEMALE <form:radiobutton path="gender" value="FEMALE"/>
                        </div>

                    </div>

                    <div class="input-box">
                        <i class="fas fa-user"></i>
                        <form:input type="text" path="firstName" name="firstName"
                                    placeholder="Enter your firstName"/>
                        <form:errors path="firstName" cssClass="text-danger"/>
                    </div>
                    <div class="input-box">
                        <i class="fas fa-user"></i>
                        <form:input type="text" path="lastName" name="lastName"
                                    placeholder="Enter your lastName"/>
                        <form:errors path="lastName" cssClass="text-danger"/>
                    </div>
                    <div class="input-box">
                        <i class="fas fa-user"></i>
                        <form:input type="text" path="username" name="userName"
                                    placeholder="Enter your userName"/>
                        <form:errors path="username" cssClass="text-danger"/>
                    </div>
                    <div class="input-box">
                        <i class="fas fa-envelope"></i>
                        <form:input type="text" path="email" name="email" placeholder="Enter your email"/>
                        <form:errors path="email" cssClass="text-danger"/>
                    </div>
                    <div class="input-box">
                        <i class="fas fa-lock"></i>
                        <form:input type="text" path="password" name="password"
                                    placeholder="Enter your password"/>
                        <form:errors path="password" cssClass="text-danger"/>
                    </div>
                    <div >
                        <input type="file" id="image" name="image">
                    </div>
                    <div class="button input-box">
                        <input type="submit" name="register">
                    </div>
                    <div class="text sign-up-text">Already have an account? <label <%--for="flip"--%>>
                        <a href="<c:url value="/expertLogin"/>">Login now</a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<script href="<c:url value="/static/js/register.js"/>"></script>


</body>

