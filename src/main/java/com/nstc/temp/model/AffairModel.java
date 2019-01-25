package com.nstc.temp.model;

import java.util.Date;

/** �������� */
public class AffairModel {
    /** id���� */
    private Integer id;
    /** �������ʹ��� */
    private String affairCode;
    /** ���뵥λ���� */
    private String cltno;
    /** ������������ */
    private Date actDate;
    /** �������� */
    private String descrption;
    /** �������� */
    private String upfilename;
    /** ״̬ 0�����桢1���ύ��2������ͨ����3������ */
    private Integer status;
    /** ������userno */
    private String approvePerson;
    /** ����ʱ�� */
    private Date approveTime;
    /** ������id */
    private Integer bizcaseid;
    /** ��¼�����ˣ������ˣ�userno */
    private String createPerson;
    /** ��¼����ʱ�䣨����ʱ�䣩 */
    private Date createTime;
    /** ��¼�޸���userno */
    private String updatePerson;
    /** ��¼�޸�ʱ�� */
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
    public String getDescrption() {
        return descrption;
    }
    public void setDescrption(String descrption) {
        this.descrption = descrption;
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
    public String toString() {
        return "AffairModel [id=" + id + ", affairCode=" + affairCode + ", cltno=" + cltno + ", actDate=" + actDate
                + ", descrption=" + descrption + ", upfilename=" + upfilename + ", status=" + status
                + ", approvePerson=" + approvePerson + ", approveTime=" + approveTime + ", bizcaseid=" + bizcaseid
                + ", createPerson=" + createPerson + ", createTime=" + createTime + ", updatePerson=" + updatePerson
                + ", updateTime=" + updateTime + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }
    
    
}
