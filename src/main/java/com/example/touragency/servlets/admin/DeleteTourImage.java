package com.example.touragency.servlets.admin;

import com.example.touragency.context.Container;
import com.example.touragency.dao.admin.TourImageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-image")
public class DeleteTourImage extends HttpServlet {
    private final TourImageDAO tourImageDAO = Container.tour_image_dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = Integer.parseInt(req.getParameter("tour_id"));
        int imageId = Integer.parseInt(req.getParameter("image_id"));
        tourImageDAO.delete(imageId);

        // For redirection, full path of a server must be provided
        resp.sendRedirect("http://localhost:8080/TourAgency_war_exploded/view-tour?tour_id=" + tourId);
    }
}
