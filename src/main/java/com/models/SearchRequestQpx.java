package com.models;

import java.util.HashMap;
import java.util.Map;

// What is a SearchRequestQpx
public class SearchRequestQpx {

    public SearchRequestQpx(SearchRequest searchRequest) {
        this.request = new HashMap<String, Object>();
        this.slice[0] = new Slice(searchRequest.getOrigin(), searchRequest.getDestination(), searchRequest.getDate());
        if(!searchRequest.getIsOneWay()){
            this.slice[1] = new Slice(searchRequest.getDestination(), searchRequest.getOrigin(), searchRequest.getReturnDate());
        }
        this.passengers = new Passenger(searchRequest.getPassengers());
        this.solutions = searchRequest.getSolutions();

        request.put("slice", this.slice);
        request.put("passengers", this.passengers);
        request.put("solutions", this.solutions);
    }


    //region request
    Map<String, Object> request;

    public Map<String, Object> getRequest() {

        return request;
    }

    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }
    //endregion

    //region slice
    private Object[] slice = new Object[2];

    public class Slice {
        public static final String SLICE = "slice";
        private String origin;
        private String destination;
        private String date;

        public Slice(String origin, String destination, String date) {
            this.origin = origin;
            this.destination = destination;
            this.date = date;
        }

        public String getOrigin() {

            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
    //endregion

    //region passenger;
    private Passenger passengers;

    public class Passenger {
        private int passenger;

        public int getAdultCount() {
            return passenger;
        }

        public void setAdultCount(int passengers) {
            this.passenger = passengers;
        }

        public Passenger(int passengers) {

            this.passenger = passengers;
        }
    }
    //endregion

    private int solutions;
}
