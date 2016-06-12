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
    
    public String modifyRecord(TrafficSchema aTrafficSchema, MissionSchema aMissionSchema) {
	session = HibernateUtil.getSession();
	Transaction tTransaction = session.beginTransaction();
	try {
	    MissionSchema tMissionSchema = missionDao.getByMissionId(session, aMissionSchema.getMissionid(), "1000000001");
	    aMissionSchema.setId(tMissionSchema.getId());
	    aMissionSchema.setSubmissionid(tMissionSchema.getSubmissionid());
	    aMissionSchema.setMissionprop1(tMissionSchema.getMissionprop1());
	    aTrafficSchema.setSerialNo(tMissionSchema.getMissionprop1());
	    TrafficSchema tTrafficSchema = trafficDao.query(session, aTrafficSchema);
	    aTrafficSchema.setId(tTrafficSchema.getId());
	    session.clear();
	    trafficDao.update(session, aTrafficSchema);
	    missionDao.update(session, aMissionSchema);
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
    
    public String inuw(TrafficSchema aTrafficSchema, MissionSchema aMissionSchema) {
	session = HibernateUtil.getSession();
	aMissionSchema.setActivityid("1000000002");
	MissionSchema tMissionSchema = missionDao.getByMissionId(session, aMissionSchema.getMissionid(), "1000000002");
	aTrafficSchema.setSerialNo(tMissionSchema.getMissionprop1());
	TrafficSchema tTrafficSchema = trafficDao.query(session, aTrafficSchema);
	tTrafficSchema.setUwflag(aTrafficSchema.getUwflag());
	tTrafficSchema.setRemark(aTrafficSchema.getRemark());
	
	String tUwFlag = aTrafficSchema.getUwflag();
	String tActivityId = "1000000003";//默认审核通
	String tSubMissionid = tMissionSchema.getSubmissionid();
	if ("1".equals(tUwFlag)) {
		tActivityId = "1000000003";
	} else if ("2".equals(tUwFlag)) {//回退
		tActivityId = "1000000001";
		int tIdx = Integer.valueOf(tSubMissionid) + 1;
		tSubMissionid = String.valueOf(tIdx);
		
	} else {
		tActivityId = "1000000000";
	}
	tMissionSchema.setActivityid(tActivityId);
	tMissionSchema.setSubmissionid(tSubMissionid);
	tMissionSchema.setModifydate(aMissionSchema.getModifydate());
	tMissionSchema.setModifytime(aMissionSchema.getModifytime());
	session.clear();
	Transaction tTransaction = session.beginTransaction();
	try {
	    trafficDao.update(session, tTrafficSchema);
	    missionDao.update(session, tMissionSchema);
	    tTransaction.commit();
	    session.flush();
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
