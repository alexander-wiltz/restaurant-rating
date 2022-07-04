package servlet;

import dao.UserDaoImpl;
import dao.exceptions.UserNotFoundException;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

@WebServlet("/session")
public class SessionCheckServlet extends HttpServlet {
    private static final long serialVersionUID = -8477142098780393269L;

    public SessionCheckServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String source = request.getParameter("s");

        if(user != null) {
            if (Objects.equals(source, "profile")) {
                try {
                    Collection<User> users = UserDaoImpl.getInstance().getUserByName(user.getUsername());
                    for (User u : users) {
                        if (u.getUsername().equals(user.getUsername())) {
                            user = u;
                        }
                    }
                } catch (UserNotFoundException e) {
                    e.getMessage();
                    request.setAttribute("error", e.getMessage());
                }
                request.setAttribute("user", user);
                session.setAttribute("user",user);
                getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
            } else if (Objects.equals(source, "rating")) {
                getServletContext().getRequestDispatcher("/rating").forward(request, response);
            }
        }

        if (Objects.equals(source, "register")) {
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        } else if (Objects.equals(source, "login") || Objects.equals(source, "rating")) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
