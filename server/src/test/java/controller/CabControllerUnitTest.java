package controller;

import com.datarepublic.simplecab.controller.CabController;
import com.datarepublic.simplecab.domain.CabRequest;
import com.datarepublic.simplecab.domain.CabResponse;
import com.datarepublic.simplecab.service.CabManagementService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CabControllerUnitTest {
    private CabController cabController = new CabController();

    @Mock
    private CabManagementService cabManagementService;

    @Before
    public void setUp(){
        cabController.setCabManagementService(cabManagementService);
    }

    @Test
    public void testFindTripWhenDateFormatIsIncorrectThrowsParseExceptionAndReturnsNull(){
        CabRequest cabRequest = getCabRequest("2013-12");
        List<CabResponse> trips = cabController.findTrip(cabRequest, false);
        Assert.assertNull(trips);
    }

    private CabRequest getCabRequest(String pickupDate){
        CabRequest cabRequest = new CabRequest();
        cabRequest.setPickupDate(pickupDate);
        return cabRequest;
    }
}
