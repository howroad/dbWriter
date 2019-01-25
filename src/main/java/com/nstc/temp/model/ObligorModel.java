package com.nstc.temp.model;

import java.util.Date;

/**  */
public class ObligorModel {
    /**  */
    private Integer id;
    /** 担保人是nsclient表的数据，内部单位 */
    private String cltno;
    /** 被担保人编号 */
    private String no;
    /** 被担保人名称 */
    private String name;
    /** 内外部类型,1内部，0外部 */
    private Integer type;
    /** 企业性质 */
    private Integer compNature;
    /** 现状 */
    private String currentSituation;
    /** 是否被诉 */
    private Integer isJudged;
    /** 控股比例 */
    private Double percent;
    /** 关联往来单位 */
    private Integer relatedCorp;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
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
