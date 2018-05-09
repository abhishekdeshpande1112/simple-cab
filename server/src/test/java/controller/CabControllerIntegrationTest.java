package controller;

import com.datarepublic.simplecab.config.DBConfig;
import com.datarepublic.simplecab.controller.CabController;
import com.datarepublic.simplecab.dao.CabDao;
import com.datarepublic.simplecab.dao.CabDaoImpl;
import com.datarepublic.simplecab.domain.CabRequest;
import com.datarepublic.simplecab.domain.CabResponse;
import com.datarepublic.simplecab.service.CabManagementService;
import com.datarepublic.simplecab.service.CabManagementServiceImpl;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class CabControllerIntegrationTest {
    @Configuration
    static class Config {

        @Bean
        public CabController getCabController() {
            return new CabController();
        }

        @Bean
        public CabManagementService getCabManagementService() {
            return new CabManagementServiceImpl();
        }

        @Bean
        public CabDao getCabDao() {
            return new CabDaoImpl();
        }

        @Bean
        public LocalSessionFactoryBean getSessionFactory() {
            DBConfig dbConfig = new DBConfig();
            ReflectionTestUtils.setField(dbConfig, DBConfig.class, "DRIVER", "com.mysql.jdbc.Driver", String.class);
            ReflectionTestUtils.setField(dbConfig, DBConfig.class, "URL", "jdbc:mysql://localhost:3306/test_db", String.class);
            ReflectionTestUtils.setField(dbConfig, DBConfig.class, "USERNAME", "root", String.class);
            ReflectionTestUtils.setField(dbConfig, DBConfig.class, "PASSWORD", "qwer1234", String.class);
            ReflectionTestUtils.setField(dbConfig, DBConfig.class, "DIALECT", "org.hibernate.dialect.MySQL5Dialect", String.class);
            ReflectionTestUtils.setField(dbConfig, DBConfig.class, "SHOW_SQL", "true", String.class);
            ReflectionTestUtils.setField(dbConfig, DBConfig.class, "HBM2DDL_AUTO", "update", String.class);
            ReflectionTestUtils.setField(dbConfig, DBConfig.class, "PACKAGES_TO_SCAN", "com", String.class);
            return dbConfig.sessionFactory();
        }
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CabDao cabDao;

    @Autowired
    private CabManagementService cabManagementService;

    @Autowired
    private CabController cabController;

    @Before
    public void setup(){
        cabController.setCabManagementService(cabManagementService);
    }

    @Test
    public void testFindTripWhenListOfMedallionsAndPickupDateAsInputReturnsNumberOfTrips(){
        List<CabResponse> tripDetails = cabController.findTrip(getCabRequest(), true);
        Assert.isTrue(!tripDetails.isEmpty(), "The number of trips are greater or equal to 1");
    }

    private CabRequest getCabRequest(){
        CabRequest cabRequest = new CabRequest();
        List<String> medallions = new ArrayList<String>(){{add("B672154F0FD3D6B5277580C3B7CBBF8E");}};
        cabRequest.setMedallions(medallions);
        cabRequest.setPickupDate("2013-12-01");
        return cabRequest;
    }
}
