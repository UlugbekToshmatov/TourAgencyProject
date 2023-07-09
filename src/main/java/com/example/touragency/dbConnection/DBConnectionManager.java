package com.example.touragency.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.touragency.context.Container;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnectionManager {
    private static Connection connection = null;
    // Unless you set the logging level to DEBUG explicitly, it does not log messages at DEBUG level
    private static final Logger LOGGER = LogManager.getLogger(DBConnectionManager.class);
    public static Connection getConnection() {
//        Connection connection = null;
        try {
            if (connection == null) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tour_agency",
                        "tour_agency_user", "root");
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

//        System.out.println(connection != null? "Connected to DB...": "Error connecting!");
        if (connection != null)
//            LOGGER.log(Level.DEBUG, "Connected to DataBase...");
//            LOGGER.debug("Connected to DataBase...");
            System.out.println("Connected to DataBase...");
        else
//            LOGGER.log(Level.ERROR, "Error connecting!");
//            LOGGER.error("Error connecting!");
            System.out.println("Error connecting!");

        return connection;
    }

    public static void initTables() {
        String createUsers = """
                create table if not exists users(
                    user_id bigserial primary key,
                    phone_number varchar(20) unique not null,
                    password varchar(100),
                    first_name varchar(50),
                    last_name varchar(50),
                    age integer,
                    gender boolean,
                    role varchar(5),
                    status varchar(10)
                );""";

        String createTours = """
                create table if not exists tours(
                    id serial primary key,
                    title varchar(250),
                    description text,
                    venue varchar(250),
                    price numeric,
                    overview_image_path varchar(150),
                    tour_date date,
                    capacity integer,
                    seats_left integer,
                    status varchar(10)
                );""";

        String createUsersToursTable = """
                create table if not exists users_tours_table (
                    id bigserial primary key,
                    user_id bigint,
                    tour_id integer,
                    tour_date date,
                    available_seats_remaining integer,
                    action varchar(15),
                    FOREIGN KEY (user_id) REFERENCES users(user_id),
                    FOREIGN KEY (tour_id) REFERENCES tours(id)
                );
                """;

        String createTourImages = """
                create table if not exists tour_images(
                    id bigserial primary key,
                    tour_id integer,
                    image_path varchar(150),
                    FOREIGN KEY (tour_id) REFERENCES tours(id)
                );""";

        // Order of creation of the tables matters as the lower tables refer to the above tables
        execute(createUsers);
        execute(createTours);
        execute(createUsersToursTable);
        execute(createTourImages);
        LOGGER.log(Level.DEBUG, "DataBase Created...");
        LOGGER.debug("DataBase Created...");
    }

    private static void execute(String sql) {
        Connection connection = getConnection();
        try {
//            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            try {
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
