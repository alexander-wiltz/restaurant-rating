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
            <form action="deleteUser" method="post">
                <table class="table-profile-welcome">
                    <tr>
                        <td><span id="profile-welcome">Welcome <c:out value="${user.username}" default="?John Doe?" />!</span></td>
                    </tr>
                    <tr>
                        <td align="center"><button type="submit" id="delete-btn" title="that really kills your profile and your whole ratings...">Delete Profile
                        </button></td>
                    </tr>
                </table>
            </form>
            <div class="navi">
                <hr>
                <a href="logout" id="logout">Logout</a> | <a href="index.jsp" id="search">Search</a> | <a href="session?s=rating" id="rate">Rate</a>
                <hr>
            </div>
            <table class="table-profile-result">
                <tr>
                    <th>Name</th>
                    <th>Comment</th>
                    <th>Grade</th>
                    <th></th>
                </tr>
                <c:forEach items="${user.ratings}" var="r">
                        <tr>
                            <td><c:out value="${r.osm.geoPointName}" default="No name-attribute for Id: ${r.osm.id}" /></td>
                            <td><c:out value="${r.txt}" default="No description" /></td>
                            <td><c:out value="${r.grade}" default="No grade added" /></td>
                            <td>
                                <form action="deleteRating" method="post">
                                    <input type="hidden" name="ratingId" value="<c:out value="${r.id}"/>">
                                    <button type="submit">Delete</button>
                                </form>
                            </td>
                        </tr>
                </c:forEach>
            </table>
        </div>
    </main>
    <footer></footer>
</div>
</body>
</html>