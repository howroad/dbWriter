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
