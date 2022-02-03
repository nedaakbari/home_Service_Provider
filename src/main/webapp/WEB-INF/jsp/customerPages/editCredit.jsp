<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>editCredit</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<form:form cssClass="p-3 m-3" cssStyle=""  action="/customerEditCredit" method="post">
    <p class="text-danger">${error}</p>
    <table class="table table-bordered table-striped text-info">
        <tr>
            <td>amount:</td>
            <td><input type="number"  step="0.01" name="amount"></td>
        </tr>

        <tr>
            <td>
                <div class="button input-box">
                    <button type="submit" name="customerEditCredit">charge</button>
                </div>

            </td>
        </tr>
    </table>
</form:form>
</body>
</html>

