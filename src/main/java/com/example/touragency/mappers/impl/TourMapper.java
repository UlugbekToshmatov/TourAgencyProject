package com.example.touragency.mappers.impl;

import com.example.touragency.beans.Tour;
import com.example.touragency.enums.Status;
import com.example.touragency.mappers.RowMapper;
import com.example.touragency.mappers.SetMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class TourMapper implements RowMapper<Tour>, SetMapper<Tour> {
    private final Logger LOGGER = LogManager.getLogger(TourMapper.class);
    @Override
    public Tour mapRow(ResultSet resultSet) {
        try {
            if (resultSet.next())
                return getTour(resultSet);

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown!");
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Set<Tour> mapSet(ResultSet resultSet) {
        Set<Tour> set = new LinkedHashSet<>();

        try {
            while (resultSet.next()) {
                set.add(getTour(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown!");
            throw new RuntimeException(e);
        }

        return set;
    }

    private Tour getTour(ResultSet resultSet) throws SQLException {
            Tour tour = new Tour();
            tour.setId(resultSet.getInt("id"));
            tour.setTitle(resultSet.getString("title"));
            tour.setDescription(resultSet.getString("description"));
            tour.setVenue(resultSet.getString("venue"));
            tour.setPrice(resultSet.getDouble("price"));
            tour.setOverviewImagePath(resultSet.getString("overview_image_path"));
            tour.setTourDate(resultSet.getDate("tour_date").toLocalDate());
            tour.setCapacity(resultSet.getInt("capacity"));
            tour.setSeatsLeft(resultSet.getInt("seats_left"));
            tour.setStatus(Status.valueOf(resultSet.getString("status")));
            return tour;
    }
}
