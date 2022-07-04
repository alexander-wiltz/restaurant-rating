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
            <form method="post" action="register">
                <table class="table-register">
                    <tr>
                        <td><div class="error">${error}</div></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="user" id="user-register-input" placeholder="Username"></td>
                    </tr>
                    <tr>
                        <td><input type="email" name="email" id="email-register-input" placeholder="Email"></td>
                    </tr>
                    <tr>
                        <td><input type="password" name="pwd" id="pwd-register-input" placeholder="Password"></td>
                    </tr>
                    <tr>
                        <td><button type="submit" id="register-btn">Register</button></td>
                    </tr>
                </table>
            </form>
            <div class="navi">
                <hr>
                <a href="index.jsp" id="search">Search</a> | <a href="session?s=login" id="login">Login</a>
                <hr>
            </div>
        </div>
    </main>
    <footer></footer>
</div>
</body>
</html>