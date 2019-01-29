package com.nstc.temp.model;

import java.util.Date;

/** 事务申请 */
public class Affair {
    /** ID主键 */
    private Integer id;
    /** 事务类型代码 */
    private String affairCode;
    /** 申请单位编码 */
    private String cltno;
    /** 期望处理日期 */
    private Date actDate;
    /** 事务描述 */
    private String description;
    /** 附件名称 */
    private String upfilename;
    /** 状态 0：保存、1：提交、2：审批通过、3：驳回 */
    private Integer status;
    /** 审批人USERNO */
    private String approvePerson;
    /** 审批时间 */
    private Date approveTime;
    /** 审批意见 */
    private String approveOpinion;
    /** 工作流ID */
    private Integer bizcaseid;
    /** 记录创建人（申请人）USERNO */
    private String createPerson;
    /** 记录创建时间（申请时间） */
    private Date createTime;
    /** 记录修改人USERNO */
    private String updatePerson;
    /** 记录修改时间 */
    private Date updateTime;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAffairCode() {
        return affairCode;
    }
    public void setAffairCode(String affairCode) {
        this.affairCode = affairCode;
    }
    public String getCltno() {
        return cltno;
    }
    public void setCltno(String cltno) {
        this.cltno = cltno;
    }
    public Date getActDate() {
        return actDate;
    }
    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUpfilename() {
        return upfilename;
    }
    public void setUpfilename(String upfilename) {
        this.upfilename = upfilename;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getApprovePerson() {
        return approvePerson;
    }
    public void setApprovePerson(String approvePerson) {
        this.approvePerson = approvePerson;
    }
    public Date getApproveTime() {
        return approveTime;
    }
    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }
    public String getApproveOpinion() {
        return approveOpinion;
    }
    public void setApproveOpinion(String approveOpinion) {
        this.approveOpinion = approveOpinion;
    }
    public Integer getBizcaseid() {
        return bizcaseid;
    }
    public void setBizcaseid(Integer bizcaseid) {
        this.bizcaseid = bizcaseid;
    }
    public String getCreatePerson() {
        return createPerson;
    }
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getUpdatePerson() {
        return updatePerson;
    }
    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
}
