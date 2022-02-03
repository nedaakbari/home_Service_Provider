<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>Category List</h1>
<table border="2" width="70%" cellpadding="2">
    <tr>
        <th>title</th>
        <th>SubCategory</th>
    </tr>
    <c:forEach var="category" items="${list}">
        <tr>
            <td>${category.title}</td>

            <td>
                <a href="showSubCategory/${category.title}">Show SubCategorys</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>

