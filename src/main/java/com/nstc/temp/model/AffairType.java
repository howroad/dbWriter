package com.nstc.temp.model;

import java.util.Date;

/** �������� */
public class AffairType {
    /** ������ */
    private String affairCode;
    /** �������� */
    private String affairName;
    /** ��¼������USERNO */
    private String createPerson;
    /** ��¼����ʱ�� */
    private Date createTime;
    /** ��¼�޸���USERNO */
    private String updatePerson;
    /** ��¼�޸�ʱ�� */
    private Date updateTime;
    /** �Ƿ���1����0������ */
    private Integer openState;
    /** ���� */
    private String affairDescription;
    public String getAffairCode() {
        return affairCode;
    }
    public void setAffairCode(String affairCode) {
        this.affairCode = affairCode;
    }
    public String getAffairName() {
        return affairName;
    }
    public void setAffairName(String affairName) {
        this.affairName = affairName;
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
    public Integer getOpenState() {
        return openState;
    }
    public void setOpenState(Integer openState) {
        this.openState = openState;
    }
    public String getAffairDescription() {
        return affairDescription;
    }
    public void setAffairDescription(String affairDescription) {
        this.affairDescription = affairDescription;
    }
    
}
