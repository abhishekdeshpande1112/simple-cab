package com.datarepublic.simplecab.dao;

import com.datarepublic.simplecab.domain.CabResponse;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;

@Repository
@Transactional
public class CabDaoImpl implements CabDao{
    public static final String MEDALLIONS_CONSTANT = "medallionList";
    public static final String PICKUP_DATE_CONSTANT = "pickupDate";
    @Autowired
    private SessionFactory sessionFactory;
    private Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    @Override
    public List<CabResponse> getCountByMedallionAndPickupDate(List<String> medallions, Date pickupDate){
        StringBuilder hqlStatement = new StringBuilder("SELECT new com.datarepublic.simplecab.domain.CabResponse(medallion, count(*)) " +
                "FROM Cab as c WHERE c.medallion IN (:medallionList)");

        if(Objects.nonNull(pickupDate)){
            hqlStatement.append(" AND date(c.pickupDate) = :pickupDate");
        }
        hqlStatement.append(" GROUP BY medallion");

        Query query = getSession().createQuery(hqlStatement.toString());
        Map paramMap = getParamMap(medallions, pickupDate);
        for (String param : query.getNamedParameters()) {
            if(param.contains("List")){
                query.setParameterList(param, (List)paramMap.get(param));
            }else {
                query.setParameter(param, paramMap.get(param));
            }
        }
        return query.list();
    }

    private Map getParamMap(List<String> medallions, Date pickupDate){
        Map paramMap = new HashMap<String, String>();
        paramMap.put(MEDALLIONS_CONSTANT, medallions);
        paramMap.put(PICKUP_DATE_CONSTANT, pickupDate);
        return paramMap;
    }
}
