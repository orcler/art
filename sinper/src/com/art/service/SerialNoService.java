package com.art.service;

import com.art.dao.SerialNoDao;
import com.art.schema.SerialNoSchema;

public class SerialNoService {
    
    private SerialNoDao serialNoDao;
    
    public synchronized String createSerialNo(String aType) {
	SerialNoSchema serialNoSchema = serialNoDao.getByType(aType);
	String tPrefix = serialNoSchema.getPrefix() == null ? "" : serialNoSchema.getPrefix().trim();
	int tMaxNo = serialNoSchema.getMaxno();
	int tLength = serialNoSchema.getNolength() - tPrefix.length() - String.valueOf(tMaxNo).length();
	String tSerialNo = tPrefix;
	for (int i = 0; i < tLength; i++) {
	    tSerialNo += "0";
	}
	tSerialNo += tMaxNo;
	tMaxNo++;
	serialNoSchema.setMaxno(tMaxNo);
	boolean isDeal = serialNoDao.save(serialNoSchema);
	if (!isDeal) {
	   System.out.println("生成主键流水号失败！");
	}
	return tSerialNo;
    }

    public SerialNoDao getSerialNoDao() {
        return serialNoDao;
    }

    public void setSerialNoDao(SerialNoDao serialNoDao) {
        this.serialNoDao = serialNoDao;
    }

}
