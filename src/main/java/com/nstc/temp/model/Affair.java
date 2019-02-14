package com.nstc.temp.model;
import java.util.Date;
/** 事务申请 */
public class Affair {
    /** ID主键 */
    private Integer id;
    /** 事务类型代码 */
    private String affairCode;
    /** 申请单位编码 */
    private String cltno;
    /** 期望处理日期 */
    private Date actDate;
    /** 事务描述 */
    private String description;
    /** 附件名称 */
    private String upfilename;
    /** 状态 0：保存、1：提交、2：审批通过、3：驳回 */
    private Integer status;
    /** 审批人USERNO */
    private String approvePerson;
    /** 审批时间 */
    private Date approveTime;
    /** 审批意见 */
    private String approveOpinion;
    /** 工作流ID */
    private Integer bizcaseid;
    /** 记录创建人（申请人）USERNO */
    private String createPerson;
    /** 记录创建时间（申请时间） */
    private Date createTime;
    /** 记录修改人USERNO */
    private String updatePerson;
    /** 记录修改时间 */
    private Date updateTime;

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setAffairCode(String affairCode){
        this.affairCode = affairCode;
    }

    public String getAffairCode(){
        return this.affairCode;
    }

    public void setCltno(String cltno){
        this.cltno = cltno;
    }

    public String getCltno(){
        return this.cltno;
    }

    public void setActDate(Date actDate){
        this.actDate = actDate;
    }

    public Date getActDate(){
        return this.actDate;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setUpfilename(String upfilename){
        this.upfilename = upfilename;
    }

    public String getUpfilename(){
        return this.upfilename;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return this.status;
    }

    public void setApprovePerson(String approvePerson){
        this.approvePerson = approvePerson;
    }

    public String getApprovePerson(){
        return this.approvePerson;
    }

    public void setApproveTime(Date approveTime){
        this.approveTime = approveTime;
    }

    public Date getApproveTime(){
        return this.approveTime;
    }

    public void setApproveOpinion(String approveOpinion){
        this.approveOpinion = approveOpinion;
    }

    public String getApproveOpinion(){
        return this.approveOpinion;
    }

    public void setBizcaseid(Integer bizcaseid){
        this.bizcaseid = bizcaseid;
    }

    public Integer getBizcaseid(){
        return this.bizcaseid;
    }

    public void setCreatePerson(String createPerson){
        this.createPerson = createPerson;
    }

    public String getCreatePerson(){
        return this.createPerson;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getCreateTime(){
        return this.createTime;
    }

    public void setUpdatePerson(String updatePerson){
        this.updatePerson = updatePerson;
    }

    public String getUpdatePerson(){
        return this.updatePerson;
    }

    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    public Date getUpdateTime(){
        return this.updateTime;
    }

}
