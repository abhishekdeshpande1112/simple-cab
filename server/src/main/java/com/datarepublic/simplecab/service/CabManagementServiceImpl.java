package com.datarepublic.simplecab.service;

import com.datarepublic.simplecab.dao.CabDao;
import com.datarepublic.simplecab.domain.CabResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by DeshpandeA1 on 13/01/2018.
 */

@Service
public class CabManagementServiceImpl implements CabManagementService {
    private static Logger logger = LoggerFactory.getLogger(CabManagementServiceImpl.class);

    @Autowired
    private CabDao cabDao;

    /*
     * @see com.datarepublic.simplecab.service#getCountByMedallionAndPickupDatetime(List, java.util.Date)
     */
    @Override
    @Cacheable(value="trips")
    public List<CabResponse> getCountByMedallionAndPickupDate(List<String> medallions, Date pickupDate){
        Long startTimeInMillSecForWebCrawling = System.currentTimeMillis();
        List<CabResponse> cabTripDetails = cabDao.getCountByMedallionAndPickupDate(medallions, pickupDate);
        logger.info("TimeTaken in ms ->" + (System.currentTimeMillis()-startTimeInMillSecForWebCrawling));
        return cabTripDetails;
    }

    @CacheEvict(value = "trips", allEntries = true)
    public void resetAllEntries() {
        // Intentionally blank
    }
}
