<!DOCTYPE html>
<html lang="de">
    <head>
        <title>Restaurant Rating</title>
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
                    <form method="get" action="search">
                        <table class="table-search">
                            <tr>
                                <td><input type="text" name="search-input" id="search-input" placeholder="Search..."></td>
                                <td><button type="submit" id="search-btn"><i class="fa fa-search" aria-hidden="true"></i></button></td>
                            </tr>
                        </table>
                    </form>
                    <div class="navi">
                        <hr>
                        <a href="session?s=login" id="login">Login</a> | <a href="logout" id="logout">Logout</a> | <a href="session?s=register" id="register">Register</a> | <a href="session?s=rating" id="rate">Rate</a> | <a href="session?s=profile" id="profile">Profile</a>
                        <hr>
                    </div>
                    <div class="no-result"><c:out value="${noResult}"/></div>
                    <c:forEach items="${result}" var="r">
                        <table class="table-result" id="<c:out value="${r.geoPoint.id}" />">
                            <th colspan="3"><c:out value="${r.value}" default="-" /></th>
                            <c:forEach items="${r.geoPoint.ratings}" var="rating">
                                <tr>
                                    <td><b><c:out value="${rating.user.username}" default="-" /></b> says: </td>
                                    <td><c:out value="${rating.txt}" default="Not yet rated..." /></td>
                                    <td>Grade: <c:out value="${rating.grade}" default="-" /></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:forEach>
                </div>
            </main>
            <footer></footer>
        </div>
    </body>
</html>