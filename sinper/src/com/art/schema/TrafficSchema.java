package com.art.schema;

import java.sql.Date;

public class TrafficSchema { 
    
    private Integer id;
    private String serialNo;
    private String EngineNo;
    private String VIN;
    private String state;
    private String paymode;
    private String uwflag;
    private String model;
    private Double cost;
    private String cert;
    private Double mileage;
    private String color;
    private String attn;
    private String phone;
    private String comcode;
    private Date indate;
    private String intime;
    private Date outdate;
    private String outtime;
    private String grpname;
    private String remark;
    private String info1;
    private String info2;
    private String info3;
    
    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }
    
    public String getSerialNo() {
	return serialNo;
    }

    public void setSerialNo(String serialNo) {
	this.serialNo = serialNo;
    }
    
    public String getEngineNo() {
        return EngineNo;
    }
    public void setEngineNo(String engineNo) {
        EngineNo = engineNo;
    }
    public String getVIN() {
        return VIN;
    }
    public void setVIN(String vIN) {
        VIN = vIN;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getUwflag() {
        return uwflag;
    }
    public void setUwflag(String uwflag) {
        this.uwflag = uwflag;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public Double getCost() {
        return cost;
    }
    public void setCost(Double cost) {
        this.cost = cost;
    }
    public String getCert() {
        return cert;
    }
    public void setCert(String cert) {
        this.cert = cert;
    }
    public Double getMileage() {
        return mileage;
    }
    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getAttn() {
        return attn;
    }
    public void setAttn(String attn) {
        this.attn = attn;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getComcode() {
        return comcode;
    }
    public void setComcode(String comcode) {
        this.comcode = comcode;
    }
    public Date getIndate() {
        return indate;
    }
    public void setIndate(Date indate) {
        this.indate = indate;
    }
    public String getIntime() {
        return intime;
    }
    public void setIntime(String intime) {
        this.intime = intime;
    }
    public Date getOutdate() {
        return outdate;
    }
    public void setOutdate(Date outdate) {
        this.outdate = outdate;
    }
    public String getOuttime() {
        return outtime;
    }
    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }
    public String getGrpname() {
        return grpname;
    }
    public void setGrpname(String group) {
        this.grpname = group;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getInfo1() {
        return info1;
    }
    public void setInfo1(String info1) {
        this.info1 = info1;
    }
    public String getInfo2() {
        return info2;
    }
    public void setInfo2(String info2) {
        this.info2 = info2;
    }
    public String getInfo3() {
        return info3;
    }
    public void setInfo3(String info3) {
        this.info3 = info3;
    }

    /**
     * @return the paymode
     */
    public String getPaymode() {
	return paymode;
    }

    /**
     * @param paymode the paymode to set
     */
    public void setPaymode(String paymode) {
	this.paymode = paymode;
    }

}
