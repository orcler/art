package com.art.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.art.dao.HibernateUtil;
import com.art.schema.SerialNoSchema;

public class SerialNo {

    public synchronized String getSerialNo(String aType) {
	String tSql = " from SerialNoSchema where notype='" + aType + "' ";
	Session session = HibernateUtil.getSession();
	Query query = session.createQuery(tSql);
	List<SerialNoSchema> tList = query.list();
	if (tList == null || tList.size() == 0) {
	    return null;
	}
	SerialNoSchema tSchema = tList.get(0);
	String tPrefix = tSchema.getPrefix() == null ? "" : tSchema.getPrefix().trim();
	int tMaxNo = tSchema.getMaxno() + 1;

	int tLength = tSchema.getNolength() - tPrefix.length() - String.valueOf(tMaxNo).length();
	String tSerialNo = tPrefix;
	for (int i = 0; i < tLength; i++) {
	    tSerialNo += "0";
	}
	tSerialNo += tMaxNo;
	tSql = " set maxno=" + tMaxNo+ " where notype='WF' ";
	tSchema.setMaxno(tMaxNo);
	session.update(tSchema);
	session.flush();
	session.close();
	return tSerialNo;
    }

}
