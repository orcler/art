package com.art.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.art.dao.HibernateUtil;
import com.art.dao.IMissionDao;
import com.art.dao.ITrafficDao;
import com.art.pdf.InConfPirnt;
import com.art.pdf.OutConfPirnt;
import com.art.schema.MissionSchema;
import com.art.schema.TrafficSchema;

public class TrafficService {

    private ITrafficDao trafficDao;
    private IMissionDao missionDao;
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
	String tActivityId = "1000000003";// 默认审核通
	String tSubMissionid = tMissionSchema.getSubmissionid();
	if ("1".equals(tUwFlag)) {
	    tActivityId = "1000000003";
	} else if ("2".equals(tUwFlag)) {// 回退
	    tActivityId = "1000000001";
	    int tIdx = Integer.valueOf(tSubMissionid) + 1;
	    tSubMissionid = String.valueOf(tIdx);
	} else if("3".equals(tUwFlag)) {//审核拒绝
	}
	tMissionSchema.setActivityid(tActivityId);
	tMissionSchema.setSubmissionid(tSubMissionid);
	tMissionSchema.setModifydate(aMissionSchema.getModifydate());
	tMissionSchema.setModifytime(aMissionSchema.getModifytime());
	session.clear();
	Transaction tTransaction = session.beginTransaction();
	try {
	    if ("3".equals(tUwFlag)) {
		trafficDao.del(session, tTrafficSchema);
		missionDao.del(session, tMissionSchema);
	    } else {
		trafficDao.update(session, tTrafficSchema);
		missionDao.update(session, tMissionSchema);
	    }
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
	    missionDao.del(session, tMissionSchema);
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

    public String prtInPdf(MissionSchema aMissionSchema, String tPath) {
	session = HibernateUtil.getSession();
	MissionSchema tMissionSchema = missionDao.getByMissionId(session, aMissionSchema.getMissionid(), "1000000003");
	TrafficSchema tTrafficSchema = new TrafficSchema();
	aMissionSchema.setMissionprop1(tMissionSchema.getMissionprop1());// 借用传输文件名
	tTrafficSchema.setSerialNo(tMissionSchema.getMissionprop1());
	session.clear();
	tTrafficSchema = trafficDao.query(session, tTrafficSchema);
	try {
	    boolean tIsCreated = new InConfPirnt().printInPdf(tTrafficSchema, tPath);
	    if (tIsCreated) {
		tTrafficSchema.setInfo1("1");// 打印凭证打印标识
		session.saveOrUpdate(tTrafficSchema);
		session.flush();
		session.close();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    public String prtOutPdf(MissionSchema aMissionSchema, String tPath) {
	session = HibernateUtil.getSession();
	MissionSchema tMissionSchema = missionDao.getByMissionId(session, aMissionSchema.getMissionid(), "2000000003");
	TrafficSchema tOutTrafficSchema = new TrafficSchema();
	aMissionSchema.setMissionprop2(tMissionSchema.getMissionprop2());// 借用传输文件名

	tOutTrafficSchema.setSerialNo(tMissionSchema.getMissionprop2());
	session.clear();
	tOutTrafficSchema = trafficDao.query(session, tOutTrafficSchema);
	TrafficSchema tInTrafficSchema = new TrafficSchema();
	if (!"1".equals(tOutTrafficSchema.getPaymode())) {// 车俩入库
	    tInTrafficSchema.setSerialNo(tMissionSchema.getMissionprop1());
	    tInTrafficSchema = trafficDao.query(session, tInTrafficSchema);
	}
	try {
	    boolean tIsCreated = new OutConfPirnt().printOutPdf(tOutTrafficSchema, tInTrafficSchema, tPath);
	    if (tIsCreated) {
		tOutTrafficSchema.setInfo1("1");// 打印凭证打印标识
		session.saveOrUpdate(tOutTrafficSchema);
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
	// 获取出库车俩信息
	TrafficSchema tOutTrafficSchema = new TrafficSchema();
	tOutTrafficSchema.setSerialNo(aMissionSchema.getMissionprop2());
	tOutTrafficSchema = trafficDao.query(session, tOutTrafficSchema);
	tOutTrafficSchema.setState("2");// 出库
	tOutTrafficSchema.setRemark(aTrafficSchema.getRemark());
	tOutTrafficSchema.setOutdate(aMissionSchema.getOutdate());
	tOutTrafficSchema.setOuttime(aMissionSchema.getOuttime());
	tOutTrafficSchema.setUwflag(null);
	if (!isPay) {
	    if (aTrafficSchema.getCost() < tOutTrafficSchema.getCost()) {
		return "入库车俩价值不能小于出库车俩价值";
	    }
	    aTrafficSchema.setPaymode(null);
	    trafficDao.save(session, aTrafficSchema);
	} else {
	    tOutTrafficSchema.setPaymode("1");
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

    public String outModify(boolean isPay, TrafficSchema aTrafficSchema, MissionSchema aMissionSchema) {
	session = HibernateUtil.getSession();
	Transaction tTransaction = session.beginTransaction();
	try {
	    MissionSchema tMissionSchema = missionDao.getByMissionId(session, aMissionSchema.getMissionid(), "2000000001");
	    aMissionSchema.setId(tMissionSchema.getId());
	    String tOldPay = tMissionSchema.getMissionprop3();// 如果原是否收费
	    TrafficSchema tOldTrafficSchema = new TrafficSchema();
	    if (!"on".equals(tOldPay)) {
		tOldTrafficSchema.setSerialNo(tMissionSchema.getMissionprop1());
		tOldTrafficSchema = trafficDao.query(session, tOldTrafficSchema);
		trafficDao.del(session, tOldTrafficSchema);
	    }
	    session.clear();
	    TrafficSchema tOutTrafficSchema = new TrafficSchema();
	    tOutTrafficSchema.setSerialNo(aMissionSchema.getMissionprop2());
	    tOutTrafficSchema = trafficDao.query(session, tOutTrafficSchema);
	    tOutTrafficSchema.setOutdate(aMissionSchema.getOutdate());
	    tOutTrafficSchema.setOuttime(aMissionSchema.getOuttime());
	    tOutTrafficSchema.setRemark(aTrafficSchema.getRemark());
	    tOutTrafficSchema.setUwflag(null);

	    if (!isPay) {
		if (aTrafficSchema.getCost() < tOutTrafficSchema.getCost()) {
		    return "入库车俩价值不能小于出库车俩价值";
		}
		tOutTrafficSchema.setPaymode(null);
		trafficDao.save(session, aTrafficSchema);
	    } else {
		tOutTrafficSchema.setPaymode("1");
	    }

	    trafficDao.update(session, tOutTrafficSchema);
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

    public String outuw(TrafficSchema aTrafficSchema, MissionSchema aMissionSchema) {
	session = HibernateUtil.getSession();
	MissionSchema tMissionSchema = missionDao.getByMissionId(session, aMissionSchema.getMissionid(), "2000000002");
	aTrafficSchema.setSerialNo(tMissionSchema.getMissionprop2());
	TrafficSchema tTrafficSchema = trafficDao.query(session, aTrafficSchema);
	tTrafficSchema.setUwflag(aTrafficSchema.getUwflag());
	tTrafficSchema.setRemark(aTrafficSchema.getRemark());
	TrafficSchema tInTrafficSchema = new TrafficSchema();
	if (!"1".equals(tTrafficSchema.getPaymode())) {// 车俩入库
	    tInTrafficSchema.setSerialNo(tMissionSchema.getMissionprop1());
	    tInTrafficSchema = trafficDao.query(session, tInTrafficSchema);
	    tInTrafficSchema.setUwflag(aTrafficSchema.getUwflag());

	}
	String tUwFlag = aTrafficSchema.getUwflag();
	String tActivityId = "2000000003";// 默认审核通
	String tSubMissionid = tMissionSchema.getSubmissionid();
	if ("1".equals(tUwFlag)) {
	    tActivityId = "2000000003";
	} else if ("2".equals(tUwFlag)) {// 回退
	    tActivityId = "2000000001";
	    int tIdx = Integer.valueOf(tSubMissionid) + 1;
	    tSubMissionid = String.valueOf(tIdx);
	} else if("3".equals(tUwFlag)) {//审批拒绝后，出库车辆重新入库
	    tTrafficSchema.setState("1");
	    tTrafficSchema.setUwflag("0");
	    tTrafficSchema.setRemark(null);
	}
	tMissionSchema.setActivityid(tActivityId);
	tMissionSchema.setSubmissionid(tSubMissionid);
	tMissionSchema.setModifydate(aMissionSchema.getModifydate());
	tMissionSchema.setModifytime(aMissionSchema.getModifytime());
	session.clear();
	Transaction tTransaction = session.beginTransaction();
	try {
	    if ("3".equals(tUwFlag)) {
		if (!"1".equals(tTrafficSchema.getPaymode())) {
		    trafficDao.del(session, tInTrafficSchema);
		}
		missionDao.del(session, tMissionSchema);
		trafficDao.update(session, tTrafficSchema);
	    } else {
		if (!"1".equals(tTrafficSchema.getPaymode())) {
		    trafficDao.update(session, tInTrafficSchema);
		}
		trafficDao.update(session, tTrafficSchema);
		missionDao.update(session, tMissionSchema);
	    }
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

    public String outInConf(TrafficSchema aTrafficSchema, MissionSchema aMissionSchema) {
	session = HibernateUtil.getSession();
	MissionSchema tMissionSchema = missionDao.getByMissionId(session, aMissionSchema.getMissionid(), "2000000003");
	aTrafficSchema.setSerialNo(tMissionSchema.getMissionprop2());
	TrafficSchema tOutTrafficSchema = trafficDao.query(session, aTrafficSchema);// 出库信息
	if (null == tOutTrafficSchema.getInfo1() || "".equals(tOutTrafficSchema.getInfo1())) {
	    return "还未打印凭证，不能确认！";
	}

	TrafficSchema tInTrafficSchema = new TrafficSchema();// 入库信息
	tInTrafficSchema.setSerialNo(tMissionSchema.getMissionprop1());
	tInTrafficSchema = trafficDao.query(session, tInTrafficSchema);
	session.clear();
	if ("0".equals(tOutTrafficSchema.getUwflag())) {// 已操作出库确认
	    /*tMissionSchema.setActivityid("2000000004");
	    tMissionSchema.setModifydate(aMissionSchema.getModifydate());
	    tMissionSchema.setModifytime(aMissionSchema.getModifytime());
	    tMissionSchema.setLastoperator(aMissionSchema.getLastoperator());*/
	    missionDao.del(session, tMissionSchema);
	}

	tInTrafficSchema.setUwflag("0");
	Transaction tTransaction = session.beginTransaction();
	try {
	    trafficDao.update(session, tInTrafficSchema);
	    tTransaction.commit();
	    session.flush();
	} catch (Exception e) {
	    tTransaction.rollback();
	    e.printStackTrace();
	    return "入库确认失败，发动机号：" + tInTrafficSchema.getEngineNo();
	} finally {
	    session.close();
	}
	return null;
    }

    public String outOutConf(TrafficSchema aTrafficSchema, MissionSchema aMissionSchema) {
	session = HibernateUtil.getSession();
	MissionSchema tMissionSchema = missionDao.getByMissionId(session, aMissionSchema.getMissionid(), "2000000003");
	aTrafficSchema.setSerialNo(tMissionSchema.getMissionprop2());
	TrafficSchema tOutTrafficSchema = trafficDao.query(session, aTrafficSchema);// 出库信息
	if (null == tOutTrafficSchema.getInfo1() || "".equals(tOutTrafficSchema.getInfo1())) {
	    return "还未打印凭证，不能确认！";
	}

	TrafficSchema tInTrafficSchema = new TrafficSchema();// 入库信息
	if (!"1".equals(tOutTrafficSchema.getPaymode())) {//
	    tInTrafficSchema.setSerialNo(tMissionSchema.getMissionprop1());
	    tInTrafficSchema = trafficDao.query(session, aTrafficSchema);
	}
	session.clear();
	if ("0".equals(tInTrafficSchema.getUwflag()) || "1".equals(tOutTrafficSchema.getPaymode())) {// 已操作出库确认
	   /* tMissionSchema.setActivityid("2000000004");
	    tMissionSchema.setModifydate(aMissionSchema.getModifydate());
	    tMissionSchema.setModifytime(aMissionSchema.getModifytime());
	    tMissionSchema.setLastoperator(aMissionSchema.getLastoperator());*/
	    missionDao.del(session, tMissionSchema);
	}

	tOutTrafficSchema.setUwflag("0");
	Transaction tTransaction = session.beginTransaction();
	try {
	    trafficDao.update(session, tOutTrafficSchema);
	    tTransaction.commit();
	    session.flush();
	} catch (Exception e) {
	    tTransaction.rollback();
	    e.printStackTrace();
	    return "入库确认失败，发动机号：" + tInTrafficSchema.getEngineNo();
	} finally {
	    session.close();
	}
	return null;
    }

    public String invertApp(MissionSchema aMissionSchema) {
	session = HibernateUtil.getSession();
	Transaction tTransaction = session.beginTransaction();
	try {
	    missionDao.save(session, aMissionSchema);
	    tTransaction.commit();
	} catch (Exception e) {
	    tTransaction.rollback();
	    e.printStackTrace();
	    return "入库失败，流水号：" + aMissionSchema.getMissionprop1();
	} finally {
	    session.close();
	}
	return null;
    }

    public String invertupload(MissionSchema aMissionSchema) {
	session = HibernateUtil.getSession();
	Transaction tTransaction = session.beginTransaction();
	MissionSchema tMissionSchema = missionDao.getByMissionId(session, aMissionSchema.getMissionid(), "3000000002");
	tMissionSchema.setActivityid("3000000003");
	tMissionSchema.setLastoperator(aMissionSchema.getLastoperator());
	try {
	    missionDao.update(session, tMissionSchema);
	    tTransaction.commit();
	} catch (Exception e) {
	    tTransaction.rollback();
	    e.printStackTrace();
	    return "入库失败，流水号：" + tMissionSchema.getMissionprop1();
	} finally {
	    session.close();
	}
	return null;
    }

    public String invertConf(MissionSchema aMissionSchema) {
	session = HibernateUtil.getSession();
	Transaction tTransaction = session.beginTransaction();
	MissionSchema tMissionSchema = missionDao.getByMissionId(session, aMissionSchema.getMissionid(), "3000000003");
	tMissionSchema.setActivityid("3000000004");
	tMissionSchema.setLastoperator(aMissionSchema.getLastoperator());
	try {
	    missionDao.update(session, tMissionSchema);
	    tTransaction.commit();
	} catch (Exception e) {
	    tTransaction.rollback();
	    e.printStackTrace();
	    return "入库失败，流水号：" + tMissionSchema.getMissionprop1();
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
