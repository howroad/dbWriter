package com.nstc.temp.model;

import java.util.Date;

/**  */
public class ObligorModel {
    /**  */
    private Integer id;
    /** ��������nsclient������ݣ��ڲ���λ */
    private String cltno;
    /** �������˱�� */
    private String no;
    /** ������������ */
    private String name;
    /** ���ⲿ����,1�ڲ���0�ⲿ */
    private Integer type;
    /** ��ҵ���� */
    private Integer compNature;
    /** ��״ */
    private String currentSituation;
    /** �Ƿ��� */
    private Integer isJudged;
    /** �عɱ��� */
    private Double percent;
    /** ����������λ */
    private Integer relatedCorp;
    /** ����ʱ�� */
    private Date createTime;
    /** �޸�ʱ�� */
    private Date updateTime;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCltno() {
        return cltno;
    }
    public void setCltno(String cltno) {
        this.cltno = cltno;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getCompNature() {
        return compNature;
    }
    public void setCompNature(Integer compNature) {
        this.compNature = compNature;
    }
    public String getCurrentSituation() {
        return currentSituation;
    }
    public void setCurrentSituation(String currentSituation) {
        this.currentSituation = currentSituation;
    }
    public Integer getIsJudged() {
        return isJudged;
    }
    public void setIsJudged(Integer isJudged) {
        this.isJudged = isJudged;
    }
    public Double getPercent() {
        return percent;
    }
    public void setPercent(Double percent) {
        this.percent = percent;
    }
    public Integer getRelatedCorp() {
        return relatedCorp;
    }
    public void setRelatedCorp(Integer relatedCorp) {
        this.relatedCorp = relatedCorp;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
}
