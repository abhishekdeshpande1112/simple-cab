package com.datarepublic.simplecab.domain;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CabRequest implements Serializable {
    static final long serialVersionUID = 1;
    @NotEmpty(message = "Array of medallions must not be blank!")
    private List<String> medallions;
    private String pickupDate;

    public List<String> getMedallions() {
        return medallions;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setMedallions(List<String> medallions) {
        this.medallions = medallions;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabRequest that = (CabRequest) o;
        return Objects.equals(medallions, that.medallions) &&
                Objects.equals(pickupDate, that.pickupDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(medallions, pickupDate);
    }

    @Override
    public String toString() {
        return "CabRequest{" +
                "medallions=" + medallions +
                ", pickupDate=" + pickupDate +
                '}';
    }
}
