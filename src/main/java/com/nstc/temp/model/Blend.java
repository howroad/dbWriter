package com.nstc.temp.model;

import java.util.Date;

/** */
public class Blend {
    /** ���� */
    private String id;
    /** ȫ��Ψһ,ʹ��BLEND+���еķ�ʽʵ�� */
    private String no;
    /** ��wf_master_user���userId��Ӧ */
    private Integer userId;
    /** Yyyy-MM-dd 24HH:mm:ss */
    private Date operateTime;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Date getOperateTime() {
        return operateTime;
    }
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
    
}
