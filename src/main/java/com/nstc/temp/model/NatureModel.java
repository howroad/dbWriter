package com.nstc.temp.model;
/** ����������� */
public class NatureModel {
    /** ID */
    private Integer id;
    /** ҵ��Ʒ�� */
    private String bussType;
    /** �������� */
    private String natureName;
    /** ���� */
    private Integer natureValue;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getBussType() {
        return bussType;
    }
    public void setBussType(String bussType) {
        this.bussType = bussType;
    }
    public String getNatureName() {
        return natureName;
    }
    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }
    public Integer getNatureValue() {
        return natureValue;
    }
    public void setNatureValue(Integer natureValue) {
        this.natureValue = natureValue;
    }
    public String toString() {
        return "NatureModel [id=" + id + ", bussType=" + bussType + ", natureName=" + natureName + ", natureValue="
                + natureValue + "]";
    }
    
}
