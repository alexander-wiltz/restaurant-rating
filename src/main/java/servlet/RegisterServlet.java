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
import java.util.ArrayList;
import java.util.Collection;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 9055800155987256451L;

    public RegisterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // proof if user is already logged in
        if(user != null) {
            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/profile.jsp").forward(request,response);
        }

        String username = request.getParameter("user");
        String email = request.getParameter("email");
        String password = request.getParameter("pwd");

        // Normally we should proof if values are filled, but not necessary here

        // check if user or email already exists
        Collection<User> users = new ArrayList<>();
        try {
            users.addAll(UserDaoImpl.getInstance().getUserByName(username));
        } catch (UserNotFoundException e) {
            e.getMessage();
        }

        try {
            users.addAll(UserDaoImpl.getInstance().getUserByMail(email));
        } catch (UserNotFoundException e) {
            e.getMessage();
        }

        // if not, create new user
        // if yes, throw error request.setAttribute("error", e);
        if(users.isEmpty()) {
            user = UserDaoImpl.getInstance().createUser(username, email, password);
        } else {
            Throwable e = new Throwable("User already exists!");
            request.setAttribute("error", e);
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        }

        // when creation was successful, log in and forward to profile.jsp
        request.setAttribute("user", user);
        session.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
