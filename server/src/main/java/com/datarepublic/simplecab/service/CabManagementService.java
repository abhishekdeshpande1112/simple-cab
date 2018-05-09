package com.datarepublic.simplecab.service;

import com.datarepublic.simplecab.domain.CabResponse;

import java.util.Date;
import java.util.List;

/**
 * Created by DeshpandeA1 on 13/01/2018.
 */
public interface CabManagementService {
    /**
     * Get number of trips for a medallion
     *
     * @param List<String> medallion
     *            List of cabId to get trip
     * @param pickupDate
     *      *            date the cab picked the trip
     * @return List<CabResponse>- List of cab with Number of trips
     */
    List<CabResponse> getCountByMedallionAndPickupDate(List<String> medallions, Date pickupDate);
    void resetAllEntries();
}
