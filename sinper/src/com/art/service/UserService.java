package com.art.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext;
import org.hibernate.Session;

import com.art.dao.HibernateUtil;
import com.art.dao.IUserDao;
import com.art.schema.MenuSchema;
import com.art.schema.UserSchema;
import com.art.util.DataSource;

public class UserService {

    private IUserDao userDao;
    private Session session;

    public IUserDao getUserDao() {
	return userDao;
    }

    public void setUserDao(IUserDao userDao) {
	this.userDao = userDao;
    }

    public boolean login(String aUserId, String aPwd) {
	session = HibernateUtil.getSession();
	System.out.println(session);
	UserSchema user = userDao.getUserById(session, aUserId);
	session.close();
	if (user != null) {
	    if (aPwd.equals(user.getPwd())) {
		return true;
	    }
	}
	return false;
    }

    public List<MenuSchema> loadMenu(String aUserId) {
	List<MenuSchema> tAllMenus = queryMenu(aUserId);
	List<MenuSchema> tTopMenus = new ArrayList<MenuSchema>();
	for (MenuSchema menu : tAllMenus) {// 获取顶级菜单
	    if ("0".equals(menu.getNodeLevel())) {
		tTopMenus.add(menu);
		loadChildren(menu, tAllMenus);
	    }
	}
	return tTopMenus;
    }

    public void loadChildren(MenuSchema aMenu, List<MenuSchema> aAllMenus) {
	for (MenuSchema menu : aAllMenus) {
	    if (menu.getParentNodeCode().equals(aMenu.getNodeCode())) {
		aMenu.addChildren(menu);
		System.out.println(menu.getNodeCode() + "------------" + menu.getParentNodeCode() + "---------------" + menu.getRunScript());
		loadChildren(menu, aAllMenus);
	    }
	}
    }
    
    public List<MenuSchema> queryMenu(String aUserId) {
	List<MenuSchema> tMenuList = new ArrayList<MenuSchema>();
	String tSql = " select d.nodecode,d.nodename,d.parentnodecode,d.nodelevel,d.runscript from IUSER a, usertomenugrp b, menugrptomenu c, menu d"
		+ "  where a.USERID=b.userid and b.menugrpcode=c.menugrpcode and c.nodecode=d.nodecode and a.USERID='" + aUserId + "' order by d.nodecode  ";
	Connection tConn = new DataSource().openConn();
	Statement tState = null;
	ResultSet tResultSet = null;
	try {
	    tState = tConn.createStatement();
	    tResultSet = tState.executeQuery(tSql);
	    while (tResultSet.next()) {
		MenuSchema tMenuSchema = new MenuSchema();
		tMenuSchema.setNodeCode(tResultSet.getString(1));
		tMenuSchema.setNodeName(tResultSet.getString(2));
		tMenuSchema.setParentNodeCode(tResultSet.getString(3));
		tMenuSchema.setNodeLevel(tResultSet.getString(4));
		tMenuSchema.setRunScript(tResultSet.getString(5));
		tMenuList.add(tMenuSchema);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    new DataSource().closeConn(tConn, tState, tResultSet);
	}
	return tMenuList;
    } 
}
