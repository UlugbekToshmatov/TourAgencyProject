package com.example.touragency.dto;

public class TourImageAndTourDTO {
    private long tourImageId;
    private int tourReferenceId;
    private String imagePath;
    private int tourId;
    private String tourTitle;
    private String tourDescription;
    private String tourVenue;
    private double tourPrice;
    private String tourOverviewImagePath;

    public long getTourImageId() {
        return tourImageId;
    }

    public void setTourImageId(long tourImageId) {
        this.tourImageId = tourImageId;
    }

    public int getTourReferenceId() {
        return tourReferenceId;
    }

    public void setTourReferenceId(int tourReferenceId) {
        this.tourReferenceId = tourReferenceId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getTourTitle() {
        return tourTitle;
    }

    public void setTourTitle(String tourTitle) {
        this.tourTitle = tourTitle;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public String getTourVenue() {
        return tourVenue;
    }

    public void setTourVenue(String tourVenue) {
        this.tourVenue = tourVenue;
    }

    public double getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(double tourPrice) {
        this.tourPrice = tourPrice;
    }

    public String getTourOverviewImagePath() {
        return tourOverviewImagePath;
    }

    public void setTourOverviewImagePath(String tourOverviewImagePath) {
        this.tourOverviewImagePath = tourOverviewImagePath;
    }
}
