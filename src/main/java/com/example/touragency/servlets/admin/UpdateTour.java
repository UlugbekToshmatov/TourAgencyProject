package com.example.touragency.servlets.admin;

import com.example.touragency.beans.Tour;
import com.example.touragency.beans.TourImage;
import com.example.touragency.context.Container;
import com.example.touragency.dao.admin.TourDAO;
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
import java.util.Set;

@WebServlet("/update")
@MultipartConfig
public class UpdateTour extends HttpServlet {
    private final TourDAO tourDAO = Container.tour_dao;
    private final TourImageDAO tourImageDAO = Container.tour_image_dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = Integer.parseInt(req.getParameter("tour_id"));

        req.setAttribute("tour", tourDAO.findById(tourId));
        req.getRequestDispatcher("/html/admin/update_tour.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String venue = req.getParameter("venue");
        double price = Double.parseDouble(req.getParameter("price"));
        Part part = req.getPart("image");
        String imagePath = part.getSubmittedFileName();
        String resourcePath = req.getServletContext().getRealPath("") + "images";
        File file = new File(resourcePath);
        part.write(resourcePath + File.separator + imagePath);

        Tour tour = new Tour();
        tour.setId(tourId);
        tour.setTitle(title);
        tour.setDescription(description);
        tour.setVenue(venue);
        tour.setPrice(price);
        tour.setOverviewImagePath(imagePath);

        tourDAO.update(tour);

//        Set<Tour> set = tourDAO.getAll();

//        // If image is updated, that image must be saved to tour_images as well
//        for (Tour tourFound : set) {
//            if (tour.getTitle().equals(tourFound.getTitle()) &&
//                    tour.getDescription().equals(tourFound.getDescription()) &&
//                    tour.getVenue().equals(tourFound.getVenue()) &&
//                    tour.getPrice() == tourFound.getPrice() &&
//                    tour.getOverviewImagePath().equals(tourFound.getOverviewImagePath())) {
//                Set<TourImage> images = tourImageDAO.findImagesByTourId(tourFound.getId());
//                boolean exists = false;
//                for (TourImage image : images) {
//                    if (image.getImagePath().equals(tourFound.getOverviewImagePath())) {
//                        exists = true;
//                        break;
//                    }
//                }
//
//                if (!exists) {
//                    TourImage image = new TourImage();
//                    image.setTourId(tourFound.getId());
//                    image.setImagePath(tourFound.getOverviewImagePath());
//
//                    tourImageDAO.save(image);
//                }
//                break;
//            }
//        }

//        req.setAttribute("tours", set);

        resp.sendRedirect("http://localhost:8080/TourAgency_war_exploded/view-all-tours");
    }
}
