package com.nstc.temp.model;

import java.util.Date;

/** ҵ����־ */
public class BusinessLogModel {
    /** LOG_ID */
    private Integer logId;
    /** ҵ��ID */
    private Integer bussId;
    /** ��־���� */
    private String logType;
    /** ����ID */
    private Integer fmid;
    /** ������ */
    private String creator;
    /** ����ʱ�� */
    private Date createTime;
    /** ��ע */
    private String memo;
    public Integer getLogId() {
        return logId;
    }
    public void setLogId(Integer logId) {
        this.logId = logId;
    }
    public Integer getBussId() {
        return bussId;
    }
    public void setBussId(Integer bussId) {
        this.bussId = bussId;
    }
    public String getLogType() {
        return logType;
    }
    public void setLogType(String logType) {
        this.logType = logType;
    }
    public Integer getFmid() {
        return fmid;
    }
    public void setFmid(Integer fmid) {
        this.fmid = fmid;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String toString() {
        return "BusinessLogModel [logId=" + logId + ", bussId=" + bussId + ", logType=" + logType + ", fmid=" + fmid
                + ", creator=" + creator + ", memo=" + memo + "]";
    }
    
}
