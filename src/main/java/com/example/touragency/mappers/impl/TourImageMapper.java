package com.example.touragency.mappers.impl;

import com.example.touragency.beans.Tour;
import com.example.touragency.beans.TourImage;
import com.example.touragency.mappers.RowMapper;
import com.example.touragency.mappers.SetMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class TourImageMapper implements RowMapper<TourImage>, SetMapper<TourImage> {
    private final Logger LOGGER = LogManager.getLogger(TourImageMapper.class);
    @Override
    public TourImage mapRow(ResultSet resultSet) {
        try {
            if (resultSet.next())
                return getTourImage(resultSet);

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown!");
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Set<TourImage> mapSet(ResultSet resultSet) {
        Set<TourImage> set = new LinkedHashSet<>();

        try {
            while (resultSet.next()) {
                set.add(getTourImage(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception thrown!");
            throw new RuntimeException(e);
        }

        return set;
    }

    private TourImage getTourImage(ResultSet resultSet) throws SQLException {
        TourImage tourImage = new TourImage();
        tourImage.setId(resultSet.getLong("id"));
        tourImage.setTourId(resultSet.getInt("tour_id"));
        tourImage.setImagePath(resultSet.getString("image_path"));

        return tourImage;
    }
}
