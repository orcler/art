package com.art.util;

public class PubFun {
    
    public SerialNo mSerialNo;

    public synchronized String getSerialNo(String aType) {
	if (mSerialNo == null)
	    mSerialNo = new SerialNo();
	return mSerialNo.getSerialNo(aType);
    }
    
}
