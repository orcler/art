package com.art.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.art.dao.HibernateUtil;
import com.art.dao.IMissionDao;
import com.art.dao.ITrafficDao;
import com.art.pdf.InConfPirnt;
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
    
    
    public String inconf(TrafficSchema aTrafficSchema, MissionSchema aMissionSchema) {
	session = HibernateUtil.getSession();
	MissionSchema tMissionSchema = missionDao.getByMissionId(session, aMissionSchema.getMissionid(), "1000000003");
	aTrafficSchema.setSerialNo(tMissionSchema.getMissionprop1());
	TrafficSchema tTrafficSchema = trafficDao.query(session, aTrafficSchema);
	if (null == tTrafficSchema.getInfo1() || "".equals(tTrafficSchema.getInfo1())) {
	    return "还未打印凭证，不能确认！";
	}
	tTrafficSchema.setUwflag("0");
	tMissionSchema.setActivityid("1000000004");
	tMissionSchema.setModifydate(aMissionSchema.getModifydate());
	tMissionSchema.setModifytime(aMissionSchema.getModifytime());
	tMissionSchema.setLastoperator(aMissionSchema.getLastoperator());
	Transaction tTransaction = session.beginTransaction();
	try {
	    trafficDao.update(session, tTrafficSchema);
	    missionDao.update(session, tMissionSchema);
	    tTransaction.commit();
	    session.flush();
	} catch (Exception e) {
	    tTransaction.rollback();
	    e.printStackTrace();
	    return "入库确认失败，发动机号：" + tTrafficSchema.getEngineNo();
	} finally {
	    session.close();
	}
	return null;
    }
    
    public String inbatchRecord(List<TrafficSchema> trafficList, List<MissionSchema> missionList) {
	session = HibernateUtil.getSession();
	Transaction tTransaction = session.beginTransaction();
	try {
		for (TrafficSchema schema : trafficList) {
			trafficDao.save(session, schema);
		}
		for (MissionSchema schema : missionList) {
			missionDao.save(session, schema);
		}
	    tTransaction.commit();
	} catch (Exception e) {
	    tTransaction.rollback();
	    e.printStackTrace();
	    return "批量入库失败：" + e.toString();
	} finally {
	    session.close();
	}
	return null;
    }
    
    public String printPdf(MissionSchema aMissionSchema, String tPath) {
    	session = HibernateUtil.getSession();
    	MissionSchema tMissionSchema = missionDao.getByMissionId(session, aMissionSchema.getMissionid(), "1000000003");
    	TrafficSchema tTrafficSchema = new TrafficSchema();
    	aMissionSchema.setMissionprop1(tMissionSchema.getMissionprop1());//借用传输文件名
    	tTrafficSchema.setSerialNo(tMissionSchema.getMissionprop1());
    	session.clear();
    	tTrafficSchema = trafficDao.query(session, tTrafficSchema);
    	try {
			boolean tIsCreated = new InConfPirnt().printInPdf(tTrafficSchema, tPath);
			if (tIsCreated) {
			    tTrafficSchema.setInfo1("1");//打印凭证打印标识
			    session.saveOrUpdate(tTrafficSchema);
			    session.flush();
			    session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    
    public String outRecord(boolean isPay, TrafficSchema aTrafficSchema, MissionSchema aMissionSchema) {
  	session = HibernateUtil.getSession();
  	//获取出库车俩信息
  	TrafficSchema tOutTrafficSchema = new TrafficSchema(); 
  	tOutTrafficSchema.setSerialNo(aMissionSchema.getMissionprop2());
  	tOutTrafficSchema = trafficDao.query(session, tOutTrafficSchema);
  	tOutTrafficSchema.setState("2");//出库
  	tOutTrafficSchema.setInfo3("pay");
  	tOutTrafficSchema.setOutdate(aTrafficSchema.getIndate());
  	tOutTrafficSchema.setOuttime(aTrafficSchema.getIntime());
  	if  (!isPay) {
  	  trafficDao.save(session, aTrafficSchema);
    	if (aTrafficSchema.getCost() < tOutTrafficSchema.getCost()) {
    	    return "入库车俩价值不能小于出库车俩价值";
    	}
  	}
  	Transaction tTransaction = session.beginTransaction();
  	try {
  	    trafficDao.update(session, tOutTrafficSchema);
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
    
    
    public String outuw(TrafficSchema aTrafficSchema, MissionSchema aMissionSchema) {
	session = HibernateUtil.getSession();
	MissionSchema tMissionSchema = missionDao.getByMissionId(session, aMissionSchema.getMissionid(), "2000000002");
	aTrafficSchema.setSerialNo(tMissionSchema.getMissionprop2());
	TrafficSchema tTrafficSchema = trafficDao.query(session, aTrafficSchema);
	tTrafficSchema.setUwflag(aTrafficSchema.getUwflag());
	tTrafficSchema.setRemark(aTrafficSchema.getRemark());
	
	String tUwFlag = aTrafficSchema.getUwflag();
	String tActivityId = "2000000003";//默认审核通
	String tSubMissionid = tMissionSchema.getSubmissionid();
	if ("1".equals(tUwFlag)) {
		tActivityId = "2000000003";
	} else if ("2".equals(tUwFlag)) {//回退
		tActivityId = "2000000001";
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
