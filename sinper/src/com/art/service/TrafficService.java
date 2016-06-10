package com.art.service;

import com.art.dao.ITrafficDao;
import com.art.schema.TrafficSchema;

public class TrafficService {

    private ITrafficDao trafficDao;
    
    public boolean save(TrafficSchema aTrafficSchema) {
	trafficDao.save(aTrafficSchema);
	return false;
    }
    

    /**
     * @return the trafficDao
     */
    public ITrafficDao getTrafficDao() {
	return trafficDao;
    }

    /**
     * @param trafficDao the trafficDao to set
     */
    public void setTrafficDao(ITrafficDao trafficDao) {
	this.trafficDao = trafficDao;
    }
    
}
