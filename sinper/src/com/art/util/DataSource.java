package com.art.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataSource {
	
	private String url = "jdbc:mysql://10.1.3.243:3306/sinper?useUnicode=true&amp;characterEncoding=UTF-8";
	private String user="root";
	private String pwd = "123456";
	
	public Connection openConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn =  DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeConn(Connection aConn, Statement aStatement, ResultSet aResultSet) {
		try {
			if (aResultSet != null)
				aResultSet.close();
		} catch (Exception e) {}
		try {
			if (aStatement != null)
				aStatement.close();
		} catch (Exception e) {}
		try {
			if (aConn != null)
				aConn.close();
		} catch (Exception e) {}
	}
	
	public static void main(String[] args) {
		Connection conn = new DataSource().openConn();
		System.out.println(conn);
	}

}
