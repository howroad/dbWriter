package com.nstc.temp.model;

import java.util.Date;
import com.nstc.dbwriter.model.Paging;

/** 账户表查询条件 */
public class AmountScope {
    
    /** 主键 */
    private Integer amountId;
    
    /** 账户名称 */
    private String amountName;
    
    /** 账户余额 */
    private Double amountBalence;
    
    /** 修改日期 */
    private Date updateTime;
    
    /** 创建者 */
    private String createPerson;
    
    /** 是否锁定 */
    private Integer locked;
    
    /** 分页对象 */
    private Paging paging;
    
    public void setAmountId(Integer amountId){
        this.amountId = amountId;
    }
    public Integer getAmountId(){
        return this.amountId;
    }
    public void setAmountName(String amountName){
        this.amountName = amountName;
    }
    public String getAmountName(){
        return this.amountName;
    }
    public void setAmountBalence(Double amountBalence){
        this.amountBalence = amountBalence;
    }
    public Double getAmountBalence(){
        return this.amountBalence;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    public Date getUpdateTime(){
        return this.updateTime;
    }
    public void setCreatePerson(String createPerson){
        this.createPerson = createPerson;
    }
    public String getCreatePerson(){
        return this.createPerson;
    }
    public void setLocked(Integer locked){
        this.locked = locked;
    }
    public Integer getLocked(){
        return this.locked;
    }
    public void setPaging(Paging paging) {
        this.paging = paging;
    }
    public Paging getPaging() {
        return paging;
    }
    
}
