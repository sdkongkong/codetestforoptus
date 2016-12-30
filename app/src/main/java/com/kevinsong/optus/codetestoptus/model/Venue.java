package com.kevinsong.optus.codetestoptus.model;


public class Venue {
    public int id;
    public String name;
    public Distance fromcentral;
    public Location location;


    public static class Distance {
        public String car;
        public String train;
    }

    public static class Location {
        public double latitude;
        public double longitude;
    }
}
