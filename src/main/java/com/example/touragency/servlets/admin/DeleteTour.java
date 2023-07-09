package com.example.touragency.servlets.admin;

import com.example.touragency.context.Container;
import com.example.touragency.dao.admin.TourDAO;
import com.example.touragency.dao.admin.TourImageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete")
public class DeleteTour extends HttpServlet {
    private final TourDAO tourDAO = Container.tour_dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = Integer.parseInt(req.getParameter("tour_id"));
        tourDAO.delete(tourId);

        resp.sendRedirect("http://localhost:8080/TourAgency_war_exploded/view-all-tours");
    }
}
