package servlet;

import dao.GeoPointValueDaoImpl;
import dao.RatingDaoImpl;
import dao.UserDaoImpl;
import dao.exceptions.PasswordErrorException;
import dao.exceptions.UserNotFoundException;
import model.GeoPoint;
import model.GeoPointValue;
import model.Rating;
import model.User;
import utils.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -4248498240961980525L;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // user is already logged in<
        if (user != null) {
            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
        }

        // proof login data
        String username = request.getParameter("username");
        String password = request.getParameter("pwd");

        try {
            Collection<User> users = UserDaoImpl.getInstance().getUserByName(username);
            for (User u : users) {
                if (u.getUsername().equals(username)) {
                    user = u;
                }
            }
        } catch (UserNotFoundException e) {
            e.getMessage();
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        byte[] userpwd = null;
        try {
            userpwd = Password.generatePasswordHash(password, user.getPasswordSalt());
            if (Arrays.equals(userpwd, user.getPasswordHash())) {

                request.setAttribute("user", user);
                session.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
            } else {
                throw new PasswordErrorException("Wrong Password!");
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (PasswordErrorException e) {
            request.setAttribute("error", e);
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}