package com.art.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PubFun {
    
    public static SerialNo mSerialNo = new SerialNo();

    public static synchronized String getSerialNo(String aType) {
	if (mSerialNo == null)
	    mSerialNo = new SerialNo();
	return mSerialNo.getSerialNo(aType);
    }
    
    public static java.sql.Date getCurSqlDate() {
	java.sql.Date tDate = new java.sql.Date(new Date().getTime());
	return tDate;
    }
    
    public static String getCurDate() {
	return getCurDate("yyyy-MM-dd");
    };
    
    public static String getCurDate(String aPattern) {
	return getDate(new Date(), aPattern);
    }
    
    public static String getDate(Date aDate, String aPattern) {
	DateFormat tDF = new SimpleDateFormat(aPattern);
	String tDate = tDF.format(aDate);
	return tDate;
    }
    
    public static String getCurTime() {
	return getCurDate("HH:mm:ss");
    };
    
    public static String getDate(String aDate, String aPattern) {
 	DateFormat df = new SimpleDateFormat(aPattern);
 	try {
 	    Date tDate = df.parse(aDate);
 	    return getDate(tDate, "yyyy-MM-dd");
 	} catch (ParseException e) {
 	    e.printStackTrace();
 	   return null;
 	}
     }
    
    public static java.sql.Date getSqlDate(String aDate, String aPattern) {
 	DateFormat df = new SimpleDateFormat(aPattern);
 	try {
 	    return new java.sql.Date(df.parse(aDate).getTime());
 	} catch (ParseException e) {
 	   return null;
 	}
     }
    
    
    public static void main(String[] args) {
	
    }
}
