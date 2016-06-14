package com.art.service;

import java.util.List;

import org.hibernate.Session;

import com.art.dao.HibernateUtil;
import com.art.dao.ICodeDao;

public class CodeService {

    private Session session;
    private ICodeDao codeDao;
    
    /**
     * 返回json格式
     * @param aSql
     * @return
     */
    public List codeQuery(String aSql) {
	session = HibernateUtil.getSession();
	List tList = codeDao.query(session, aSql);
	session.close();
	return tList;
    }


    public ICodeDao getCodeDao() {
        return codeDao;
    }

    public void setCodeDao(ICodeDao codeDao) {
        this.codeDao = codeDao;
    }

    
}
