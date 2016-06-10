package com.art.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.art.dao.HibernateUtil;
import com.art.dao.ITrafficDao;
import com.art.schema.TrafficSchema;

public class TraficDaoImpl implements ITrafficDao {

    @Override
    public boolean save(TrafficSchema aTrafficSchema) {
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
    public boolean del(TrafficSchema aTrafficSchema) {
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
    public boolean update(TrafficSchema aTrafficSchema) {
	Session session = HibernateUtil.getSession();
	Transaction tTransaction;
	tTransaction = session.beginTransaction();
	try {
	    session.update(aTrafficSchema);
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
    public List query(String aSql) {
	Session session = HibernateUtil.getSession();
	List tList = session.createQuery(aSql).list();
	return tList;
    }

}
