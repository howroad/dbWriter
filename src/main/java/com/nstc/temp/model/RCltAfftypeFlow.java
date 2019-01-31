package com.nstc.temp.model;
/** 单位事物类型工作流关系表 */
public class RCltAfftypeFlow {
    /** 关系表主键 */
    private Integer id;
    /** 单位编号 */
    private String cltno;
    /** 事物类型编号 */
    private String affairCode;
    /** 流程编号 */
    private String fwKey;

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setCltno(String cltno){
        this.cltno = cltno;
    }

    public String getCltno(){
        return this.cltno;
    }

    public void setAffairCode(String affairCode){
        this.affairCode = affairCode;
    }

    public String getAffairCode(){
        return this.affairCode;
    }

    public void setFwKey(String fwKey){
        this.fwKey = fwKey;
    }

    public String getFwKey(){
        return this.fwKey;
    }

}
