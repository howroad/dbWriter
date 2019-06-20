package com.nstc.temp.model;

import java.util.Date;

/**
 * <p>Title: Attach.java</p>
 *
 * <p>Description: 附件信息表</p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019-06-20 14:32:21
 *
 */
public class Attach {
    
    /** 附件Id */
    private Integer attachId;
    
    /** 附件名称 */
    private String attachName;
    
    /** 附件路径 */
    private String filePath;
    
    /** 附件类型编号 */
    private String attNo;
    
    /** 是否删除 */
    private Integer isDelete;
    
    /** 单位编号 */
    private String cltno;
    
    /** 单位名称 */
    private String cltname;
    
    /** 创建时间 */
    private Date createTime;
    
    /** 创建人员 */
    private String createPerson;
    

    public void setAttachId(Integer attachId){
        this.attachId = attachId;
    }
    public Integer getAttachId(){
        return this.attachId;
    }
    public void setAttachName(String attachName){
        this.attachName = attachName;
    }
    public String getAttachName(){
        return this.attachName;
    }
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
    public String getFilePath(){
        return this.filePath;
    }
    public void setAttNo(String attNo){
        this.attNo = attNo;
    }
    public String getAttNo(){
        return this.attNo;
    }
    public void setIsDelete(Integer isDelete){
        this.isDelete = isDelete;
    }
    public Integer getIsDelete(){
        return this.isDelete;
    }
    public void setCltno(String cltno){
        this.cltno = cltno;
    }
    public String getCltno(){
        return this.cltno;
    }
    public void setCltname(String cltname){
        this.cltname = cltname;
    }
    public String getCltname(){
        return this.cltname;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    public Date getCreateTime(){
        return this.createTime;
    }
    public void setCreatePerson(String createPerson){
        this.createPerson = createPerson;
    }
    public String getCreatePerson(){
        return this.createPerson;
    }
    
    public String toString() {
        return "Attach ["
                + "attachId = " + attachId + ", "
                + "attachName = " + attachName + ", "
                + "filePath = " + filePath + ", "
                + "attNo = " + attNo + ", "
                + "isDelete = " + isDelete + ", "
                + "cltno = " + cltno + ", "
                + "cltname = " + cltname + ", "
                + "createTime = " + createTime + ", "
                + "createPerson = " + createPerson + " "
                + "]";
    }
}
