package com.art.schema;

import java.io.Serializable;
import java.sql.Date;

public class MissionSchema implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String missionid;
    private String submissionid;
    private String processid;
    private String activityid;
    private String activitystatus;
    private String missionprop1;
    private String missionprop2;
    private String missionprop3;
    private String defaultoperator;
    private String lastoperator;
    private String createoperator;
    private Date makedate;
    private String maketime;
    private Date modifydate;
    private String modifytime;
    private Date indate;
    private String intime;
    private Date outdate;
    private String outtime;
    private Date standenddate;
    private Date standendtime;
    private String operatecom;
    private String mainmissionid;
    
    public Integer getId() {
	return id;
    }
    
    public void setId(Integer id) {
	this.id = id;
    }
    public String getMissionid() {
        return missionid;
    }
    public void setMissionid(String missionid) {
        this.missionid = missionid;
    }
    public String getSubmissionid() {
        return submissionid;
    }
    public void setSubmissionid(String submissionid) {
        this.submissionid = submissionid;
    }
    public String getProcessid() {
        return processid;
    }
    public void setProcessid(String processid) {
        this.processid = processid;
    }
    public String getActivityid() {
        return activityid;
    }
    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }
    public String getActivitystatus() {
        return activitystatus;
    }
    public void setActivitystatus(String activitystatus) {
        this.activitystatus = activitystatus;
    }
    public String getMissionprop1() {
        return missionprop1;
    }
    public void setMissionprop1(String missionprop1) {
        this.missionprop1 = missionprop1;
    }
    public String getMissionprop2() {
        return missionprop2;
    }
    public void setMissionprop2(String missionprop2) {
        this.missionprop2 = missionprop2;
    }
    public String getMissionprop3() {
        return missionprop3;
    }
    public void setMissionprop3(String missionprop3) {
        this.missionprop3 = missionprop3;
    }
    public String getDefaultoperator() {
        return defaultoperator;
    }
    public void setDefaultoperator(String defaultoperator) {
        this.defaultoperator = defaultoperator;
    }
    public String getLastoperator() {
        return lastoperator;
    }
    public void setLastoperator(String lastoperator) {
        this.lastoperator = lastoperator;
    }
    public String getCreateoperator() {
        return createoperator;
    }
    public void setCreateoperator(String createoperator) {
        this.createoperator = createoperator;
    }
    public Date getMakedate() {
        return makedate;
    }
    public void setMakedate(Date makedate) {
        this.makedate = makedate;
    }
    public String getMaketime() {
        return maketime;
    }
    public void setMaketime(String maketime) {
        this.maketime = maketime;
    }
    public Date getModifydate() {
        return modifydate;
    }
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
    public String getModifytime() {
        return modifytime;
    }
    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
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
    public Date getStandenddate() {
        return standenddate;
    }
    public void setStandenddate(Date standenddate) {
        this.standenddate = standenddate;
    }
    public Date getStandendtime() {
        return standendtime;
    }
    public void setStandendtime(Date standendtime) {
        this.standendtime = standendtime;
    }
    public String getOperatecom() {
        return operatecom;
    }
    public void setOperatecom(String operatecom) {
        this.operatecom = operatecom;
    }
    public String getMainmissionid() {
        return mainmissionid;
    }
    public void setMainmissionid(String mainmissionid) {
        this.mainmissionid = mainmissionid;
    }
    
}
