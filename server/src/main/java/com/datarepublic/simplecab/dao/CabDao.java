package com.datarepublic.simplecab.dao;

import com.datarepublic.simplecab.domain.CabResponse;

import java.util.Date;
import java.util.List;

public interface CabDao {
    List<CabResponse> getCountByMedallionAndPickupDate(List<String> medallions, Date pickupDate);
}
