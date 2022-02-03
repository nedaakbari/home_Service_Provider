<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>searcgUsers</title>
    <style>

    </style>
</head>
<body>

<%
    Object admin = session.getAttribute("admin");
%>
<c:if test="${sessionScope.admin eq null}">
    <h1 class="display-5 text-center pt-5">Access Denied</h1>
</c:if>
<div class="wrapper">
    <!--Top menu -->
    <div class="section">
        <div class="top_navbar">
            <div class="hamburger">
                <a href="#">
                    <i class="fas fa-bars"></i>
                </a>
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
                <a href="<c:url value="/adminDashbord"/>">
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
                <a href="<c:url value="/manageExperts"/>">
                    <span class="icon"><i class="fas fa-tachometer-alt"></i></span>
                    <span class="item">ManageExperts</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/verifyUsers"/>">
                    <span class="icon"><i class="fas fa-database"></i></span>
                    <span class="item">verifyUsers</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/mangeUser"/>">
                    <span class="icon"><i class="fas fa-chart-line"></i></span>
                    <span class="item">ReportsUser</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/users"/>">
                    <span class="icon"><i class="fas fa-search"></i></span>
                    <span class="item">searchUsers</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/searchExperts"/>">
                    <span class="icon"><i class="fas fa-cog"></i></span>
                    <span class="item">searchExperts</span>
                </a>
            </li>
        </ul>
    </div>


    <%--body--%>
    <div class="">
        <div class="row mt-2">
                <form:form cssClass="m-5 p-5 text-center" cssStyle="width: 1100px" action="/users/searchExperts"
                           method="get">
                    <table class="table table-striped  table-hover" style="margin-left: 180px"><%--table-dark--%>
                        <tr>
                            <td>
                                <input name="firstName" placeHolder="firstName"/>
                            </td>
                            <td>
                                <input name="lastName" placeHolder="lastName"/>
                            </td>
                            <td>
                                <input name="email" placeHolder="email"/>
                            </td>
                            <td>
                                <label for="category">Choose a category:</label>

                                <select name="category" id="category">
                                    <option value="BUILDING_DECORATION">BUILDING_DECORATION</option>
                                    <option value="BUILDING_FACILITIES">BUILDING_FACILITIES</option>
                                    <option value="HOME_APPLIANCES">HOME_APPLIANCES</option>
                                    <option value="HOME_CLEANING_AND_HYGIENE">HOME_CLEANING_AND_HYGIENE</option>
                                    <option value="MOVING_HELP">MOVING_HELP</option>
                                    <option value="HOME_APPLIANCES">HOME_APPLIANCES</option>
                                    <option value="VEHICLES">VEHICLES</option>
                                </select>
                            </td>
                            <td>
                                <input type="submit" value="search"/>
                            </td>
                        </tr>
                        <tr>
                            <th>firstName</th>
                            <th>lastName</th>
                            <th>email</th>
                            <th>category</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${expertList}" var="expert">
                            <tr>
                                <td>${expert.firstName}</td>
                                <td>${expert.lastName}</td>
                                <td>${expert.email}</td>
                                <td>${expert.score}</td>
                                <td>
                                    <a href="#"> datails</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </form:form>

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
