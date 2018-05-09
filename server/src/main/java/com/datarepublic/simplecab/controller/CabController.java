package com.datarepublic.simplecab.controller;

import com.datarepublic.simplecab.domain.CabRequest;
import com.datarepublic.simplecab.domain.CabResponse;
import com.datarepublic.simplecab.service.CabManagementService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DeshpandeA1 on 13/01/2018.
 */

@Controller
@EnableAutoConfiguration
@RequestMapping("cab")
public class CabController {
    private static Logger logger = LoggerFactory.getLogger(CabController.class);

    @Autowired
    private CabManagementService cabManagementService;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public static final String CAB_CONTROLLER_LOG_PREFIX = "\nCab Service->";

    @RequestMapping(value="/trips", method=RequestMethod.POST)
    @ResponseBody
    public List<CabResponse> findTrip(@Valid @RequestBody CabRequest request,
                                      @RequestParam(value = "ignoreCache", required = false) boolean ignoreCache) {
        logger.info("Request for number of trips cab made for medallion: {}, pickupDate: {}", request.getMedallions(), request.getPickupDate());
        /*if(Objects.isNull(request.getMedallions()) || request.getMedallions().isEmpty()){
            return null;
        }*/
        try {
            Date pickupDate = null;
            if(StringUtils.isNotEmpty(request.getPickupDate())){
                pickupDate = formatter.parse(request.getPickupDate());
            }
            if(ignoreCache){
                clearCache();
            }
            List<CabResponse> cabTripDetails = cabManagementService.getCountByMedallionAndPickupDate(request.getMedallions(), pickupDate);
            logger.info("Cab Service is successful!");
            return cabTripDetails;
        } catch (ParseException e) {
            logger.error(CAB_CONTROLLER_LOG_PREFIX+ " Cannot format input pickup date."+ e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/cache/clear", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void clearCache() {
        logger.info("Request for clearing cache");
        cabManagementService.resetAllEntries();
        logger.info("Cache cleared successfully");
    }

    public void setCabManagementService(CabManagementService cabManagementService) {
        this.cabManagementService = cabManagementService;
    }
}