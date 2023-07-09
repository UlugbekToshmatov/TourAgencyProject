package com.example.touragency.listeners;

import com.example.touragency.context.Container;
import com.example.touragency.dbConnection.DBConnectionManager;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

//@WebListener
public class ApplicationListener implements ServletContextListener {
    private final Logger LOGGER = LogManager.getLogger(ApplicationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Connection set up...");
        System.out.println("Connection set up...");
        sce.getServletContext().setAttribute("connection", DBConnectionManager.getConnection());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Connection connection = (Connection) sce.getServletContext().getAttribute("connection");
        try {
            if (connection == DBConnectionManager.getConnection()) {
                connection.close();
                LOGGER.info("Connection shut down...");
                System.out.println("Connection shut down...");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
