package com.art.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.art.dao.HibernateUtil;
import com.art.dao.ITrafficDao;
import com.art.schema.TrafficSchema;

public class TraficDaoImpl implements ITrafficDao {

    @Override
    public List query(Session aSession, String aSql) {
	List tList = aSession.createQuery(aSql).list();
	return tList;
    }

    @Override
    public boolean save(Session aSession, TrafficSchema aTrafficSchema) {
	Session session = HibernateUtil.getSession();
	Transaction tTransaction;
	tTransaction = session.beginTransaction();
	try {
	    session.save(aTrafficSchema);
	    tTransaction.commit();
	} catch (Exception e) {
	    e.printStackTrace();
	    tTransaction.rollback();
	    return false;
	} finally {
	    session.close();
	}
	return true;
    }

    @Override
    public boolean del(Session aSession, TrafficSchema aTrafficSchema) {
	Session session = HibernateUtil.getSession();
	Transaction tTransaction;
	tTransaction = session.beginTransaction();
	try {
	    session.delete(aTrafficSchema);
	    tTransaction.commit();
	} catch (Exception e) {
	    tTransaction.rollback();
	    return false;
	} finally {
	    session.close();
	}
	return true;
    }

    @Override
    public boolean update(Session aSession, TrafficSchema aTrafficSchema) {
	aSession.update(aTrafficSchema);
	return true;
    }

    @Override
    public TrafficSchema query(Session aSession, TrafficSchema aTrafficSchema) {
	String tSql = "  from TrafficSchema where serialNo='" +aTrafficSchema.getSerialNo() + "' ";
	Query query = aSession.createQuery(tSql);
	TrafficSchema tSchema = (TrafficSchema) query.list().get(0);
	return tSchema;
    }

}
