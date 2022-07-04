package servlet;

import dao.GeoPointDaoImpl;
import dao.GeoPointValueDaoImpl;
import dao.RatingDaoImpl;
import dao.exceptions.GeoPointNotFoundException;
import model.GeoPoint;
import model.GeoPointValue;
import model.Rating;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 3676759768283690955L;

    public SearchServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("search-input");

        Collection<GeoPointValue> geoPointValues = GeoPointValueDaoImpl.getInstance().findGeoPointByName(name);

        if(geoPointValues.isEmpty()) {
            request.setAttribute("noResult","No result...Please search again.");
        }
        request.setAttribute("result",geoPointValues);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}