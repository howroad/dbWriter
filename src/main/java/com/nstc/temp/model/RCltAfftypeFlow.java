package com.nstc.temp.model;
/** ��λ�������͹�������ϵ�� */
public class RCltAfftypeFlow {
    /** ��ϵ������ */
    private Integer id;
    /** ��λ��� */
    private String cltno;
    /** �������ͱ�� */
    private String affairCode;
    /** ���̱�� */
    private String fwKey;

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setCltno(String cltno){
        this.cltno = cltno;
    }

    public String getCltno(){
        return this.cltno;
    }

    public void setAffairCode(String affairCode){
        this.affairCode = affairCode;
    }

    public String getAffairCode(){
        return this.affairCode;
    }

    public void setFwKey(String fwKey){
        this.fwKey = fwKey;
    }

    public String getFwKey(){
        return this.fwKey;
    }

}
