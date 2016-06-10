package com.art.dao;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.art.schema.UserSchema;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
	Configuration conf = new Configuration();
	conf.configure("hibernate.cfg.xml");
	sessionFactory = conf.buildSessionFactory();
    }

    public static Session getSession() {
	return sessionFactory.openSession();
    }

    public static void main(String[] args) {
	UserSchema user = new UserSchema();
	user.setUserId("002");
	user.setName("jack");
	Date tDate = new Date(new java.util.Date().getTime());
	user.setMakedate(tDate);
	Session session = getSession();
	Transaction tTrans = session.beginTransaction();
	try {
	    session.delete(user);
	    session.save(user);
	    tTrans.commit();
	    System.out.println("success");
	} catch (Exception e) {
	    tTrans.rollback();
	    System.out.println("rollback");
	} finally {
	    session.flush();
	    session.close();
	    sessionFactory.close();
	    System.out.println("close");
	}
    }
}
