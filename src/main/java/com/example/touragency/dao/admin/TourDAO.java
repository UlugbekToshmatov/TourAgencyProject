package com.example.touragency.dao.admin;

import com.example.touragency.beans.Tour;
import com.example.touragency.context.Container;
import com.example.touragency.dbConnection.DBConnectionManager;
import com.example.touragency.dto.TourDTO;
import com.example.touragency.mappers.impl.TourMapper;
import com.example.touragency.util.DataAccessObject;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Set;

public class TourDAO extends DataAccessObject<Tour> {
    private final Connection connection = DBConnectionManager.getConnection();
    private final Logger LOGGER = LogManager.getLogger(TourDAO.class);
    private final TourMapper tourMapper = new TourMapper();
    private final String FIND_BY_ID = "select * from Tours where id=?";
    private final String GET_ALL = "select * from Tours";
    private final String INSERT = "insert into Tours (title, description, venue, price, overview_image_path, " +
            "tour_date, capacity, seats_left, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "update Tours set title=?, description=?, venue=?, price=?," +
            " overview_image_path=? where id=?";
    private final String DELETE = "delete from Tours where id=?";
    private final String DELETE_TOUR_IMAGES = "delete from tour_images where tour_id=?";
    private final String INSERT_INTO_USERS_TOURS_TABLE = "insert into users_tours_table (user_id, tour_id, " +
            "tour_date, available_seats_remaining, action) values (?, ?, ?, ?, ?)";


    @Override
    public Tour findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);

            Tour tour = tourMapper.mapRow(statement.executeQuery());

            if (tour != null)
                LOGGER.log(Level.INFO, "Tour with id=" + id + " found.");
            else
                LOGGER.log(Level.INFO, "Tour with id=" + id + " not found!");

            return tour;
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown while finding tour with id=" + id + "!");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Tour> getAll() {
        Set<Tour> tours;

        try (PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            tours = tourMapper.mapSet(resultSet);

            return tours;
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown while getting all tours!");
            throw new RuntimeException(e);
        }
    }

    // The following save method uses trigger function internally in the database to insert
    // new image path to tour_images when new tour is added
    public void save(TourDTO tourDTO) {
//        Connection connection = null;
        try {
//            connection = DBConnectionManager.getConnection();
            connection.setAutoCommit(false);

            String select = "select * from tours where title=? and description=? and venue=? and price=? and " +
                    "overview_image_path=?";
            PreparedStatement checkStatement = connection.prepareStatement(select);
            checkStatement.setString(1, tourDTO.getTitle());
            checkStatement.setString(2, tourDTO.getDescription());
            checkStatement.setString(3, tourDTO.getVenue());
            checkStatement.setDouble(4, tourDTO.getPrice());
            checkStatement.setString(5, tourDTO.getOverviewImagePath());
            Set<Tour> set = tourMapper.mapSet(checkStatement.executeQuery());

            int saved = 0;
            if (set.size() == 0) {
                saved = insertIntoTours(getTourFromDTO(tourDTO));
            }



            if (saved != 0)
                LOGGER.log(Level.INFO, "Tour saved.");
            else
                LOGGER.log(Level.INFO, "Tour not saved!");

            PreparedStatement insertIntoUsersToursTable = connection.prepareStatement(INSERT_INTO_USERS_TOURS_TABLE);
            insertIntoUsersToursTable.setLong(1, tourDTO.getUserId());
//            insertIntoUsersToursTable.setInt(2, tourDTO.);

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown while saving new tour!");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Tour entity) {
//        Connection connection = null;
        try {
//            connection = DBConnectionManager.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement insertStatement = connection.prepareStatement(INSERT);
            insertStatement.setString(1, entity.getTitle());
            insertStatement.setString(2, entity.getDescription());
            insertStatement.setString(3, entity.getVenue());
            insertStatement.setDouble(4, entity.getPrice());
            insertStatement.setString(5, entity.getOverviewImagePath());
            insertStatement.setDate(6, Date.valueOf(entity.getTourDate()));
            insertStatement.setInt(7, entity.getCapacity());
            insertStatement.setInt(8, entity.getSeatsLeft());
            insertStatement.setString(9, entity.getStatus().name());

            int saved = insertStatement.executeUpdate();
            if (saved != 0)
                LOGGER.log(Level.INFO, "Tour saved.");
            else
                LOGGER.log(Level.INFO, "Tour not saved!");

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown while saving new tour!");
            throw new RuntimeException(e);
        }
    }

    // The following update method uses trigger function internally in the database to insert
    // new image path if overview image of a tour is updated
    @Override
    public void update(Tour entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getDescription());
            statement.setString(3, entity.getVenue());
            statement.setDouble(4, entity.getPrice());
            statement.setString(5, entity.getOverviewImagePath());
            statement.setInt(6, entity.getId());

            int updated = statement.executeUpdate();
            if (updated != 0)
                LOGGER.log(Level.INFO, "Tour with id=" + entity.getId() + " updated.");
            else
                LOGGER.log(Level.INFO, "Tour with id=" + entity.getId() + " not updated!");

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown while updating tour with id=" +
                    entity.getId() + "!");
            throw new RuntimeException(e);
        }
    }

    // Following delete method first deletes images related to the current tour and then deletes
    // the provided tour by using transaction on the back-end side instead of using trigger function
    // in the database like save or update methods
    @Override
    public void delete(int id) {
//        Connection connection = null;
        try {
//            connection = DBConnectionManager.getConnection();
            PreparedStatement deleteTourImages = connection.prepareStatement(DELETE_TOUR_IMAGES);
            PreparedStatement deleteTour = connection.prepareStatement(DELETE);
            connection.setAutoCommit(false);

            deleteTourImages.setInt(1, id);
            deleteTour.setInt(1, id);

            int imagesDeleted = deleteTourImages.executeUpdate();
//            if (imagesDeleted != 0)
//                LOGGER.log(Level.INFO, "Tour images with tour id=" + id + " deleted.");
//            else {
//                LOGGER.log(Level.INFO, "Tour images with tour id=" + id + " not deleted!");
//                LOGGER.log(Level.INFO, "Error while deleting tour with id=" + id + "!");
//                throw new RuntimeException("Error while deleting tour with id=" + id + "!");
//            }

            int tourDeleted = deleteTour.executeUpdate();
            if (tourDeleted != 0) {
                LOGGER.log(Level.INFO, "Tour with id=" + id + " deleted.");
                connection.commit();
            } else {
                System.out.println("Tour with id=" + id + " not deleted.");
                LOGGER.log(Level.INFO, "Tour with id=" + id + " not deleted.");
                connection.rollback();  // This else block is hugely likely not to be run (exception is thrown instead)
            }

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
            LOGGER.log(Level.ERROR, "SQL exception thrown while deleting tour with id=" + id + "!");
            throw new RuntimeException(e);
        }
//        finally {
//            System.out.println("Inside Finally block");
//            try {
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    private int insertIntoTours(Tour tour) throws SQLException {
//        Connection connection = DBConnectionManager.getConnection();
        PreparedStatement insertStatement = connection.prepareStatement(INSERT);
        insertStatement.setString(1, tour.getTitle());
        insertStatement.setString(2, tour.getDescription());
        insertStatement.setString(3, tour.getVenue());
        insertStatement.setDouble(4, tour.getPrice());
        insertStatement.setString(5, tour.getOverviewImagePath());
        insertStatement.setDate(6, Date.valueOf(tour.getTourDate()));
        insertStatement.setInt(7, tour.getCapacity());
        insertStatement.setInt(8, tour.getSeatsLeft());
        insertStatement.setString(9, tour.getStatus().name());

        return insertStatement.executeUpdate();
    }

    private Tour getTourFromDTO(TourDTO tourDTO) {
        Tour tour = new Tour();
        tour.setTitle(tourDTO.getTitle());
        tour.setDescription(tourDTO.getDescription());
        tour.setVenue(tourDTO.getVenue());
        tour.setPrice(tourDTO.getPrice());
        tour.setOverviewImagePath(tourDTO.getOverviewImagePath());
        tour.setTourDate(tourDTO.getTourDate());
        tour.setCapacity(tourDTO.getCapacity());
        tour.setSeatsLeft(tourDTO.getSeatsLeft());
        tour.setStatus(tourDTO.getStatus());

        return tour;
    }
}
