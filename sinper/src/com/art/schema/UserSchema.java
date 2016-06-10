package com.art.schema;

import java.sql.Date;

public class UserSchema
{

	private String userId;
	private String name;
	private String pwd;
	private String state;
	private String comcode;
	private String mobile;
	private String email;
	private String operate;
	private Date makedate	;
	private String maketime;
	private Date modifydate;
	private String modifytime;
	
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String userName)
	{
		this.name = userName;
	}
	public String getPwd()
	{
		return pwd;
	}
	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}
	public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state = state;
	}
	public String getComcode()
	{
		return comcode;
	}
	public void setComcode(String comcode)
	{
		this.comcode = comcode;
	}
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getOperate()
	{
		return operate;
	}
	public void setOperate(String operate)
	{
		this.operate = operate;
	}
	public Date getMakedate()
	{
		return makedate;
	}
	public void setMakedate(Date makedate)
	{
		this.makedate = makedate;
	}
	public String getMaketime()
	{
		return maketime;
	}
	public void setMaketime(String maketime)
	{
		this.maketime = maketime;
	}
	public Date getModifydate()
	{
		return modifydate;
	}
	public void setModifydate(Date modifydate)
	{
		this.modifydate = modifydate;
	}
	public String getModifytime()
	{
		return modifytime;
	}
	public void setModifytime(String modifytime)
	{
		this.modifytime = modifytime;
	}

	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (obj instanceof UserSchema) {
		UserSchema user = (UserSchema) obj;
		return userId.equals(((UserSchema) obj).getUserId());
	    }
	    return false;
	    
	}
}
