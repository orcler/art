package com.art.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.art.dao.HibernateUtil;
import com.art.dao.IMissionDao;
import com.art.dao.ITrafficDao;
import com.art.schema.MissionSchema;
import com.art.schema.TrafficSchema;

public class TrafficService {

    private ITrafficDao trafficDao;
    private	IMissionDao missionDao;
    private Session session;
    public boolean save(TrafficSchema aTrafficSchema) {
	session = HibernateUtil.getSession();
	trafficDao.save(session, aTrafficSchema);
	session.close();
	return true;
    }

    public String inRecord(TrafficSchema aTrafficSchema, MissionSchema aMissionSchema) {
	session = HibernateUtil.getSession();
	Transaction tTransaction = session.beginTransaction();
	try {
	    trafficDao.save(session, aTrafficSchema);
	    missionDao.save(session, aMissionSchema);
	    tTransaction.commit();
	} catch (Exception e) {
	    tTransaction.rollback();
	    e.printStackTrace();
	    return "入库失败，发动机号：" + aTrafficSchema.getEngineNo();
	} finally {
	    session.close();
	}
	return null;
    }
    
    public ITrafficDao getTrafficDao() {
	return trafficDao;
    }

    public void setTrafficDao(ITrafficDao trafficDao) {
	this.trafficDao = trafficDao;
    }

    public IMissionDao getMissionDao() {
        return missionDao;
    }

    public void setMissionDao(IMissionDao missionDao) {
        this.missionDao = missionDao;
    }

}
