package com.example.touragency.servlets.admin;

import com.example.touragency.context.Container;
import com.example.touragency.dao.admin.TourDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/view-all-tours")
public class ViewAllTours extends HttpServlet {
    private final TourDAO tourDAO = Container.tour_dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tours", tourDAO.getAll());
        req.getRequestDispatcher("/html/admin/admin_tour.jsp").forward(req, resp);
    }
}
