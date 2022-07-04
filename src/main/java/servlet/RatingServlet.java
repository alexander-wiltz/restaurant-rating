package servlet;

import dao.GeoPointDaoImpl;
import dao.RatingDaoImpl;
import dao.UserDaoImpl;
import dao.exceptions.UserNotFoundException;
import model.GeoPoint;
import model.Rating;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/rating")
public class RatingServlet extends HttpServlet {
    private static final long serialVersionUID = 6029960097614358756L;

    public RatingServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // user is not logged in, there is no active session
        if (user == null) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        try {
            Collection<User> users = UserDaoImpl.getInstance().getUserByName(user.getUsername());
            for (User u : users) {
                if (u.getUsername().equals(user.getUsername())) {
                    user = u;
                }
            }
            Collection<GeoPoint> unratedGeoPointsFromUser = GeoPointDaoImpl.getInstance().findUnratedGeoPointsFromUser(user.getId());
            request.setAttribute("size",unratedGeoPointsFromUser.size());
            request.setAttribute("user",user);
            request.setAttribute("unratedGeopoints", unratedGeoPointsFromUser);
            getServletContext().getRequestDispatcher("/rate.jsp").forward(request, response);

        } catch (UserNotFoundException e) {
            request.setAttribute("error", e);
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
