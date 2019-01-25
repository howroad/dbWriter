package com.nstc.temp.model;
/** 存款性质设置 */
public class NatureModel {
    /** ID */
    private Integer id;
    /** 业务品种 */
    private String bussType;
    /** 性质名称 */
    private String natureName;
    /** 存期 */
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
