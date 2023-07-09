package com.example.touragency;

import java.io.*;
import java.util.*;

import com.example.touragency.beans.Tour;
import com.example.touragency.context.Container;
import com.example.touragency.dao.admin.TourDAO;
import com.example.touragency.dao.admin.TourImageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


// This class server as a tester class between all server pages and servlets one by one

// If enctype=multipart/form-data is set, then @MultipartConfig must be used to read parameters
@MultipartConfig
public class HelloServlet extends HttpServlet {
    TourDAO tourDAO = Container.tour_dao;
    TourImageDAO tourImageDAO = Container.tour_image_dao;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("tour_images", tourImageDAO.getAll());
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tourId = req.getParameter("tour_id");
        tourDAO.delete(Integer.parseInt(tourId));

        req.setAttribute("tours", tourDAO.getAll());
        req.getRequestDispatcher("/html/admin/admin_tour.jsp").forward(req, resp);
    }
}