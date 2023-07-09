package com.example.touragency.context;

import com.example.touragency.dao.admin.TourDAO;
import com.example.touragency.dao.admin.TourImageDAO;
import com.example.touragency.dbConnection.DBConnectionManager;

import java.sql.Connection;

public class Container {
    // This class serves as a container for dependency injection
    public final static TourDAO tour_dao = new TourDAO();
    public final static TourImageDAO tour_image_dao = new TourImageDAO();
}
