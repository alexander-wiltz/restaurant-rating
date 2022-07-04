package servlet;

import dao.RatingDaoImpl;
import dao.UserDaoImpl;
import dao.exceptions.RatingNotFoundException;
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

@WebServlet("/deleteRating")
public class DeleteRatingServlet extends HttpServlet {
    private static final long serialVersionUID = -7181680091091991473L;

    public DeleteRatingServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String ratingId = request.getParameter("ratingId");
        try {
            RatingDaoImpl.getInstance().deleteRating(Integer.parseInt(ratingId));
            Collection<User> users = UserDaoImpl.getInstance().getUserByName(user.getUsername());
            for (User u : users) {
                if (u.getUsername().equals(user.getUsername())) {
                    user = u;
                }
            }
        } catch (RatingNotFoundException | UserNotFoundException e) {
            e.printStackTrace();
        }

        request.setAttribute("user",user);
        session.setAttribute("user",user);
        getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}