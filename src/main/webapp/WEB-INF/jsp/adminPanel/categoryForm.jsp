<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/static/css/adminPanel.css"/>">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>adminPanelHome</title>
    <style>

    </style>
</head>
<body>

<div class="wrapper">
    <!--Top menu -->
    <div class="section">
        <div class="top_navbar">
            <div class="hamburger">
                <a href="#">
                    <i class="fas fa-bars"></i>
                </a>
                <button href="<c:url value="/signOut"/>" class="btn btn-outline-light" type="button"
                        style="margin-left: 970px">Logout</button>
            </div>
        </div>

    </div>

    <div class="sidebar">
        <div class="profile">
            <img src=https://ath2.unileverservices.com/wp-content/uploads/sites/4/2020/02/IG-annvmariv-1024x1016.jpg
                 alt="profile_picture">
            <h3> hello ${name}</h3>
            <p>Admin</p>
        </div>
        <!--menu item-->

        <ul>
            <li>
                <a href="<c:url value="/"/>" class="active">
                    <span class="icon"><i class="fas fa-home"></i></span>
                    <span class="item">Home</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/dashboard"/>">
                    <span class="icon"><i class="fas fa-desktop"></i></span>
                    <span class="item">My Dashboard</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/mangeCategory"/>">
                    <span class="icon"><i class="fas fa-user-friends"></i></span>
                    <span class="item">ManageCategory</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/manageExperts"/>">
                    <span class="icon"><i class="fas fa-tachometer-alt"></i></span>
                    <span class="item">ManageExperts</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/verifyUsers"/>">
                    <span class="icon"><i class="fas fa-database"></i></span>
                    <span class="item">verifyUsers</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/reportUsers"/>">
                    <span class="icon"><i class="fas fa-chart-line"></i></span>
                    <span class="item">ReportsUser</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/users"/>">
                    <span class="icon"><i class="fas fa-user-shield"></i></span>
                    <span class="item">searchUsers</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/searchExperts"/>">
                    <span class="icon"><i class="fas fa-cog"></i></span>
                    <span class="item">searchExperts</span>
                </a>
            </li>
        </ul>
    </div>


    <%--body--%>
    <div class="container">
        <div class="row mt-5">

            <div class="col-4">

            </div>
            <div class="col-8">
                <div class="panel-container">

                    <h1>Add New Category</h1>
                    <p class="text-danger">${error}</p>
                    <form:form method="post" action="/admin/saveCategory" modelAttribute="category">
                        <table class="table table-bordered table-striped  text-black" style="padding: 100px 100px 0px 100px ;!important;">
                            <tr>
                                <td>title :</td>
                                <td><form:input path="title"/>
                                <br>
                                    <form:errors path="title" cssClass="text-danger"/>
                                </td>
                            </tr>

                        </table>

                        <div class="">
                            <input type="submit" value="saveCategory"/>
                        </div>
                    </form:form>

                </div>
            </div>
        </div>

    </div>

</div>

</div>
<script>
    var hamburger = document.querySelector(".hamburger");
    hamburger.addEventListener("click", function () {
        document.querySelector("body").classList.toggle("active");
    })
</script>

</body>

</html>

