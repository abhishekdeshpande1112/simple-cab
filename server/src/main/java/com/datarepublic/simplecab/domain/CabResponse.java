package com.datarepublic.simplecab.domain;

/**
 * Created by DeshpandeA1 on 13/01/2018.
 */
public class CabResponse {
    private String medallion;
    private long numberOfTrips;

    public CabResponse() {
    }

    public CabResponse(String medallion, long numberOfTrips) {
        this.medallion = medallion;
        this.numberOfTrips = numberOfTrips;
    }

    public String getMedallion() {
        return medallion;
    }

    public void setMedallion(String medallion) {
        this.medallion = medallion;
    }

    public long getNumberOfTrips() {
        return numberOfTrips;
    }

    public void setNumberOfTrips(Integer numberOfTrips) {
        this.numberOfTrips = numberOfTrips;
    }
}
