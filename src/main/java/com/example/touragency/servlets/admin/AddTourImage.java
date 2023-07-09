package com.example.touragency.servlets.admin;

import com.example.touragency.beans.TourImage;
import com.example.touragency.context.Container;
import com.example.touragency.dao.admin.TourImageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/tour-image")
@MultipartConfig
public class AddTourImage extends HttpServlet {
    private final TourImageDAO tourImageDAO = Container.tour_image_dao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = Integer.parseInt(req.getParameter("tour_id"));
        Part part = req.getPart("image");
        String imagePath = part.getSubmittedFileName();
        String resourcePath = req.getServletContext().getRealPath("") + "images";
        File file = new File(resourcePath);
        part.write(resourcePath + File.separator + imagePath);

        TourImage image = new TourImage();
        image.setTourId(tourId);
        image.setImagePath(imagePath);

        tourImageDAO.save(image);

        // This approach is for avoiding unintended re-POSTs
        resp.sendRedirect("http://localhost:8080/TourAgency_war_exploded/view-tour?tour_id=" + tourId);
    }
}
