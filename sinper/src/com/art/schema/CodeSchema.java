package com.art.schema;

public class CodeSchema {
    
    private Integer id;
    private String codetype;
    private String code;
    private String codename;
    private String remark;
 
    public Integer getId() {
	return id;
    }
 
    public void setId(Integer id) {
	this.id = id;
    }
    public String getCodetype() {
        return codetype;
    }
    public void setCodetype(String codetype) {
        this.codetype = codetype;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getCodename() {
        return codename;
    }
    public void setCodename(String codename) {
        this.codename = codename;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
