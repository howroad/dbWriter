package com.nstc.temp.model;

import java.util.Date;

/**
 * <p>Title: Attach.java</p>
 *
 * <p>Description: ������Ϣ��</p>
 *
 * <p>Company: �����ź��ǿƼ��ɷ����޹�˾</p>
 *
 * @author luhao
 * 
 * @since��2019-06-20 14:32:21
 *
 */
public class Attach {
    
    /** ����Id */
    private Integer attachId;
    
    /** �������� */
    private String attachName;
    
    /** ����·�� */
    private String filePath;
    
    /** �������ͱ�� */
    private String attNo;
    
    /** �Ƿ�ɾ�� */
    private Integer isDelete;
    
    /** ��λ��� */
    private String cltno;
    
    /** ��λ���� */
    private String cltname;
    
    /** ����ʱ�� */
    private Date createTime;
    
    /** ������Ա */
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
