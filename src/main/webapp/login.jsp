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
            <form method="post" action="login">
                <table class="table-login">
                    <tr>
                        <td><div class="error">${error}</div></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="username" id="user-input" placeholder="Username"></td>
                    </tr>
                    <tr>
                        <td><input type="password" name="pwd" id="pwd-input" placeholder="Password"></td>
                    </tr>
                    <tr>
                        <td><button type="submit" id="login-btn">Login</button></td>
                    </tr>
                </table>
            </form>
            <div class="navi">
                <hr>
                <a href="index.jsp" id="search">Search</a> | <a href="register.jsp" id="register">Register</a>
                <hr>
            </div>
        </div>
    </main>
    <footer></footer>
</div>
</body>
</html>