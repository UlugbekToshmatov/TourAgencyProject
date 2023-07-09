package com.example.touragency.servlets.admin;

import com.example.touragency.beans.Tour;
import com.example.touragency.beans.TourImage;
import com.example.touragency.context.Container;
import com.example.touragency.dao.admin.TourDAO;
import com.example.touragency.dao.admin.TourImageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;


// This servlet is needed to avoid unintended re-POSTs if user refreshes a page
// that sends post request to the server
@WebServlet("/view-tour")
public class ViewTour extends HttpServlet {
    private final TourDAO tourDAO = Container.tour_dao;
    private final TourImageDAO tourImageDAO = Container.tour_image_dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tourId = req.getParameter("tour_id");

        if (tourId == null) {
            Set<Tour> set = tourDAO.getAll();

            req.setAttribute("tours", set);
            req.getRequestDispatcher("/html/admin/admin_tour.jsp").forward(req, resp);
        } else {

            Tour tour = tourDAO.findById(Integer.parseInt(tourId));
            Set<TourImage> images = tourImageDAO.findImagesByTourId(Integer.parseInt(tourId));

            req.setAttribute("tour", tour);
            req.setAttribute("tour_images", images);
            req.getRequestDispatcher("/html/admin/view_tour.jsp").forward(req, resp);
        }
    }
}
