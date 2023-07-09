package com.example.touragency.servlets.admin;

import com.example.touragency.beans.Tour;
import com.example.touragency.context.Container;
import com.example.touragency.dao.admin.TourDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/set-tour-image")
public class SetTourImage extends HttpServlet {
    private final TourDAO tourDAO = Container.tour_dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = Integer.parseInt(req.getParameter("tour_id"));
        String imagePath = req.getParameter("image_path");

        Tour tour = tourDAO.findById(tourId);
        tour.setOverviewImagePath(imagePath);
        tourDAO.update(tour);

        req.setAttribute("tours", tourDAO.getAll());
        req.getRequestDispatcher("html/admin/admin_tour.jsp").forward(req, resp);
    }
}
