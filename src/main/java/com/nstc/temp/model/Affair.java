package com.nstc.temp.model;

import java.util.Date;

/** �������� */
public class Affair {
    /** ID���� */
    private Integer id;
    /** �������ʹ��� */
    private String affairCode;
    /** ���뵥λ���� */
    private String cltno;
    /** ������������ */
    private Date actDate;
    /** �������� */
    private String description;
    /** �������� */
    private String upfilename;
    /** ״̬ 0�����桢1���ύ��2������ͨ����3������ */
    private Integer status;
    /** ������USERNO */
    private String approvePerson;
    /** ����ʱ�� */
    private Date approveTime;
    /** ������� */
    private String approveOpinion;
    /** ������ID */
    private Integer bizcaseid;
    /** ��¼�����ˣ������ˣ�USERNO */
    private String createPerson;
    /** ��¼����ʱ�䣨����ʱ�䣩 */
    private Date createTime;
    /** ��¼�޸���USERNO */
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
