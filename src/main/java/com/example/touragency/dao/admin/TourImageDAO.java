package com.example.touragency.dao.admin;

import com.example.touragency.beans.Tour;
import com.example.touragency.beans.TourImage;
import com.example.touragency.context.Container;
import com.example.touragency.dbConnection.DBConnectionManager;
import com.example.touragency.dto.TourImageAndTourDTO;
import com.example.touragency.mappers.impl.TourImageMapper;
import com.example.touragency.util.DataAccessObject;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class TourImageDAO extends DataAccessObject<TourImage> {
    private final Connection connection = DBConnectionManager.getConnection();
    private final TourDAO tourDAO = Container.tour_dao;
    private final Logger LOGGER = LogManager.getLogger(TourImageDAO.class);
    private final TourImageMapper tourImageMapper = new TourImageMapper();
    private final String FIND_BY_ID = "select * from tour_images where id=?";
    private final String FIND_IMAGES_BY_TOUR_ID = "select * from tour_images where tour_id=?";
    private final String GET_ALL = "select * from tour_images";
    private final String INSERT = "insert into tour_images (tour_id, image_path)" +
            " values (?, ?)";
    private final String UPDATE = "update tour_images set tour_id=?, image_path=?" +
            " where id=?";
    private final String DELETE = "delete from tour_images where id=?";
    private final String DELETE_BY_TOUR_ID = "delete from tour_images where tour_id=?";
    private final String SELECT_JOIN = "select * from tour_images as ti join tours as t " +
            "on ti.tour_id=t.id where ti.id=?";

    @Override
    public TourImage findById(int id) {
        try(PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);

            TourImage tourImage = tourImageMapper.mapRow(statement.executeQuery());

            if (tourImage != null)
                LOGGER.log(Level.INFO, "Tour image with id=" + id + " found.");
            else
                LOGGER.log(Level.INFO, "Tour image with id=" + id + " not found!");

            return tourImage;
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown while finding image with id=" + id + "!");
            throw new RuntimeException(e);
        }
    }

    public Set<TourImage> findImagesByTourId(int tourId) {
        try(PreparedStatement statement = connection.prepareStatement(FIND_IMAGES_BY_TOUR_ID)) {
            statement.setInt(1, tourId);

            return tourImageMapper.mapSet(statement.executeQuery());
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown while finding images with tour id=" + tourId + "!");
            throw new RuntimeException(e);
        }

    }

    @Override
    public Set<TourImage> getAll() {
        Set<TourImage> images;

        try(PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            images = tourImageMapper.mapSet(resultSet);

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown while getting all tour images!");
            throw new RuntimeException(e);
        }

        return images;
    }

    @Override
    public void save(TourImage entity) {
        try(PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, entity.getTourId());
            statement.setString(2, entity.getImagePath());

            int saved = statement.executeUpdate();
            if (saved != 0)
                LOGGER.log(Level.INFO, "Tour image saved.");
            else
                LOGGER.log(Level.INFO, "Tour image not saved!");

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown while saving new tour image!");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(TourImage entity) {
        // If an image is to be updated (move to another dir. or use for another tour), image id must
        // also be passed as well inside the TourImage object and image id must be unchanged when passed
        try(PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setInt(1, entity.getTourId());
            statement.setString(2, entity.getImagePath());
            statement.setLong(3, entity.getId());


            int updated = statement.executeUpdate();
            if (updated != 0)
                LOGGER.log(Level.INFO, "Tour image with id=" + entity.getId() + " updated.");
            else
                LOGGER.log(Level.INFO, "Tour image with id=" + entity.getId() + " not updated!");

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown while updating tour image with id=" +
                    entity.getId() + "!");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int imageId) {
//        Connection connection = null;
        try {
//            connection = DBConnectionManager.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement selectStatement = connection.prepareStatement(SELECT_JOIN);
            selectStatement.setLong(1, imageId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                TourImageAndTourDTO dto = new TourImageAndTourDTO();
                dto.setTourImageId(resultSet.getLong(1));
                dto.setTourReferenceId(resultSet.getInt(2));
                dto.setImagePath(resultSet.getString(3));
                dto.setTourId(resultSet.getInt(4));
                dto.setTourTitle(resultSet.getString(5));
                dto.setTourDescription(resultSet.getString(6));
                dto.setTourVenue(resultSet.getString(7));
                dto.setTourPrice(resultSet.getDouble(8));
                dto.setTourOverviewImagePath(resultSet.getString(9));

                if (dto.getImagePath().equals(dto.getTourOverviewImagePath())) {
                    Tour tour = new Tour();
                    tour.setId(dto.getTourId());
                    tour.setTitle(dto.getTourTitle());
                    tour.setDescription(dto.getTourDescription());
                    tour.setVenue(dto.getTourVenue());
                    tour.setPrice(dto.getTourPrice());
                    tour.setOverviewImagePath("last image.png");
                    tourDAO.update(tour);
                }
            }

            PreparedStatement deleteStatement = connection.prepareStatement(DELETE);
            deleteStatement.setLong(1, imageId);

            int deleted = deleteStatement.executeUpdate();
            if (deleted != 0) {
                connection.commit();
                LOGGER.log(Level.INFO, "Tour image with id=" + imageId + " deleted.");
            } else
                LOGGER.log(Level.INFO, "Tour image with id=" + imageId + " not deleted.");

        } catch (SQLException e) {
            try {
                LOGGER.log(Level.ERROR, "SQL exception thrown while deleting tour image with id=" + imageId + "!");
                connection.rollback();
                throw new RuntimeException(e);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
//        finally {
//            try {
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    // Following delete method is implemented as part of delete method in TourDAO
    public void deleteByTourId(int tourId) {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_BY_TOUR_ID)) {
            statement.setInt(1, tourId);
            int delete = statement.executeUpdate();
            if (delete != 0)
                LOGGER.log(Level.INFO, "Tour image with tour id=" + tourId + " deleted.");
            else
                LOGGER.log(Level.INFO, "Tour image with tour id=" + tourId + " not deleted!");

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown while deleting tour image with tour id=" + tourId + "!");
            throw new RuntimeException(e);
        }
    }
}
