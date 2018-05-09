package com.datarepublic.simplecab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="cab_trip_data")
public class Cab implements Serializable {
    @Id
    @Column(name="medallion")
    private String medallion;
    @Column(name="pickup_datetime")
    private Date pickupDate;

    public String getMedallion() {
        return medallion;
    }

    public void setMedallion(String medallion) {
        this.medallion = medallion;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cab cab = (Cab) o;
        return Objects.equals(medallion, cab.medallion) &&
                Objects.equals(pickupDate, cab.pickupDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medallion, pickupDate);
    }

    @Override
    public String toString() {
        return "Cab{" +
                "medallion='" + medallion + '\'' +
                ", pickupDate=" + pickupDate +
                '}';
    }
}
