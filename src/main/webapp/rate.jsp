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
            <table class="table-rate-welcome">
                <tr>
                    <td><span id="rate-welcome">Welcome <c:out value="${user.username}" default="?John Doe?" />!</span></td>
                </tr>
                <tr>
                    <td>Please rate your favorite restaurants.</td>
                </tr>
            </table>
            <div class="navi">
                <hr>
                <a href="logout" id="logout">Logout</a> | <a href="index.jsp" id="search">Search</a> | <a href="session?s=profile" id="profile">Profile</a>
                <hr>
            </div>

            <form action="createRating" method="post">
                <table class="table-rate-result">
                    <tr>
                        <td>Restaurant (<c:out value="${size}"/>):
                            <select class="restaurant-selection" name="restaurant-selection">
                                <c:forEach items="${unratedGeopoints}" var="ugp">
                                    <option value="<c:out value="${ugp.id}"/>"><c:out value="${ugp.geoPointName}" default="No name-attribute for Id: ${ugp.id}"/></option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>Grade:
                            <select class="grade-selection" name="grade-selection">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Comment:</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <textarea name="restaurant-comment" placeholder="Your comment..." maxlength="2000"></textarea>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><button name="submit-btn">Submit</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </main>
    <footer></footer>
</div>
</body>
</html>