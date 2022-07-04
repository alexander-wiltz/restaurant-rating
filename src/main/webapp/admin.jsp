<!DOCTYPE html>
<html lang="de">
<head>
    <title>Restaurant Rating - Adminpanel</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- FavIcon -->
    <link rel="icon" type="image/x-icon" href="images/favicon.ico">
    <!--Styling-->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!--JavaScript-->
    <script src=""></script>
    <!-- JSP Tags -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>
</head>
<body>
<div class="wrapper">
    <header></header>
    <main>
        <div class="container">
            <table class="table-result">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Email</th>
                </tr>
                <c:forEach items="${userlist}" var="u">
                    <tr>
                        <td><c:out value="${u.id}" default="-" /></td>
                        <td><c:out value="${u.username}" default="-" /></td>
                        <td><c:out value="${u.email}" default="-" /></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </main>
    <footer></footer>
</div>
</body>
</html>