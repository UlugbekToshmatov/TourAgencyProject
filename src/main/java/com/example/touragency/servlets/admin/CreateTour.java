package com.example.touragency.servlets.admin;

import com.example.touragency.beans.Tour;
import com.example.touragency.context.Container;
import com.example.touragency.dao.admin.TourDAO;
import com.example.touragency.enums.Status;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet("/tour")
@MultipartConfig
public class CreateTour extends HttpServlet {
    private final TourDAO tourDAO = Container.tour_dao;
//    private final TourImageDAO tourImageDAO = Container.tour_image_dao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String venue = req.getParameter("venue");
        double price = Double.parseDouble(req.getParameter("price"));
        Part part = req.getPart("image");
        String imagePath = part.getSubmittedFileName();
        String resourcePath = req.getServletContext().getRealPath("") + "images";
        File file = new File(resourcePath);
        part.write(resourcePath + File.separator + imagePath);
        LocalDate date = LocalDate.parse(req.getParameter("date"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        int capacity = Integer.parseInt(req.getParameter("capacity"));

        System.out.println("Tour with the following data created:");
        System.out.println("Tour id: " + UUID.randomUUID());
        System.out.println("Tour title: " + title);
        System.out.println("Tour description: " + description);
        System.out.println("Venue: " + venue);
        System.out.println("Price: " + price);
        System.out.println("Image path: " + imagePath);
        System.out.println("Resource path: " + resourcePath);
        System.out.println("Date: " + date);
        System.out.println("Capacity: " + capacity);

        Tour tour = new Tour();
        tour.setTitle(title);
        tour.setDescription(description);
        tour.setVenue(venue);
        tour.setPrice(price);
        tour.setOverviewImagePath(imagePath);
        tour.setTourDate(date);
        tour.setCapacity(capacity);
        tour.setSeatsLeft(capacity);
        tour.setStatus(Status.ACTIVE);

        tourDAO.save(tour);

//        Set<Tour> set = tourDAO.getAll();

//        // Todo: Perform the process of adding image to tour images inside save method of tourDAO with transaction like delete method
//        for (Tour tourFound : set) {
//            if (tour.getTitle().equals(tourFound.getTitle()) &&
//                    tour.getDescription().equals(tourFound.getDescription()) &&
//                    tour.getVenue().equals(tourFound.getVenue()) &&
//                    tour.getPrice() == tourFound.getPrice() &&
//                    tour.getOverviewImagePath().equals(tourFound.getOverviewImagePath())) {
//                TourImage image = new TourImage();
//                image.setTourId(tourFound.getId());
//                image.setImagePath(tourFound.getOverviewImagePath());
//
//                tourImageDAO.save(image);
//                break;
//            }
//        }

//        req.setAttribute("tours", set);
//        req.getRequestDispatcher("/view-all-tours").forward(req, resp);

        resp.sendRedirect("http://localhost:8080/TourAgency_war_exploded/view-all-tours");
        // If forwarded, RequestDispatcher would forward to any other servlet uri with
        // doPost method because the request from the front-end was for POST method in this case.
        // However, if redirected, response always redirects with GET method, and Browser replaces its
        // url to the redirected url.
    }
}
