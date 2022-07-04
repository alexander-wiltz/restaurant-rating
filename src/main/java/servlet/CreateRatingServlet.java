package servlet;

import dao.GeoPointDaoImpl;
import dao.RatingDaoImpl;
import dao.exceptions.GeoPointNotFoundException;
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
import java.util.List;

@WebServlet("/createRating")
public class CreateRatingServlet extends HttpServlet {
    private static final long serialVersionUID = 6029960097614358756L;

    public CreateRatingServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // user is not logged in, there is no active session
        if (user == null) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        String osmId = request.getParameter("restaurant-selection");
        String grade = request.getParameter("grade-selection");
        String comment = request.getParameter("restaurant-comment");

        List<GeoPoint> geoPoints = GeoPointDaoImpl.getInstance().findGeoPointById(Long.parseLong(osmId));
        RatingDaoImpl.getInstance().createNewRating(user, geoPoints.get(0), Integer.parseInt(grade), comment);

        session.setAttribute("user",user);
        request.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/rating").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}