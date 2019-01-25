package com.nstc.temp.model;

import java.util.Date;

/** ��Ʊ��ָ�ض��ˡ��ض�ʱ����е�Ʊ�ݣ�һ��Ʊ�ݶ�γ����γɶ�����¼ */
public class WarehouseBill {
    /**  */
    private Integer wbid;
    /**  */
    private Integer whid;
    /** Ʊ֤ID */
    private Integer blid;
    /** ���п�ʼ���� */
    private Date begindate;
    /** ���е������� */
    private Date enddate;
    /** ������� */
    private Integer locked;
    /** ������� */
    private Integer endflag;
    /**
     * R01 ����ת�� R02 ���� R03 ���� P01 �⸶
     */
    private String endtype;
    /**
     * �������������ֵǼ� 0:δ�Ǽǣ�1:�ѵǼ�
     */
    private Integer registerflag;
    /** �Ǽ�ʱ�� */
    private Date registerdate;
    /** �Ǽǽ�� */
    private Double registeramount;
    /** ����ԭ�� */
    private String registertype;
    /** Ӧ��Ʊ�ݵ�ǰ�ֱ����� */
    private String priorholder;
    /** ���е�λ��� */
    private String holder;
    /** �ո���� */
    private String recflag;
    /** �Ƿ����ת�� EM00:����ת�� EM01:����ת�� */
    private String banendrsmtmk;
    /** Ʊ֤״̬ */
    private String billstatus;
    /** �����˿������к� */
    private String holderbankno;
    /** �������˺� */
    private String holderbankaccount;
    /** Ȩ���˿������к� */
    private String ownerbankno;
    /** Ȩ�����˺� */
    private String ownerbankaccount;
    /** ��Ʊ��ʽ BILLS:��Ѻ DEPOSIT:��֤�� CREDIT:���� MULTI:��� */
    private String paybillway;
    /** ������Դ */
    private String sourceappno;
    /** ������Դ���� */
    private Integer sourceid;
    /** ����Ψһ��ʶ */
    private String bankbillsysno;
    /** ĩ�ν���ID */
    private Integer lasttxid;
    /** �����ʶ�����ڱ���ո���ϵͳ�Ƿ��Ѵ��� 0:δ���� 1:�Ѵ��� */
    private Integer processflag;
    /** ��ע */
    private String remark;
    /** ��¼����ʱ�� */
    private Date createtime;
    /** ĩ�θ���ʱ�� */
    private Date lastupdatetime;
    /** 覴ñ�ʶ */
    private Integer flawflag;
    /** �����ѽ��� */
    private Double settledamount;
    /** ���ֵǼ����� */
    private Double discountregrate;
    /** ������;���� */
    private Integer daysinway;
    /** ����˳������ */
    private Integer deferdays;
    /** ԭʼ��� */
    private Integer orgwhid;
    public Integer getWbid() {
        return wbid;
    }
    public void setWbid(Integer wbid) {
        this.wbid = wbid;
    }
    public Integer getWhid() {
        return whid;
    }
    public void setWhid(Integer whid) {
        this.whid = whid;
    }
    public Integer getBlid() {
        return blid;
    }
    public void setBlid(Integer blid) {
        this.blid = blid;
    }
    public Date getBegindate() {
        return begindate;
    }
    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }
    public Date getEnddate() {
        return enddate;
    }
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
    public Integer getLocked() {
        return locked;
    }
    public void setLocked(Integer locked) {
        this.locked = locked;
    }
    public Integer getEndflag() {
        return endflag;
    }
    public void setEndflag(Integer endflag) {
        this.endflag = endflag;
    }
    public String getEndtype() {
        return endtype;
    }
    public void setEndtype(String endtype) {
        this.endtype = endtype;
    }
    public Integer getRegisterflag() {
        return registerflag;
    }
    public void setRegisterflag(Integer registerflag) {
        this.registerflag = registerflag;
    }
    public Date getRegisterdate() {
        return registerdate;
    }
    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }
    public Double getRegisteramount() {
        return registeramount;
    }
    public void setRegisteramount(Double registeramount) {
        this.registeramount = registeramount;
    }
    public String getRegistertype() {
        return registertype;
    }
    public void setRegistertype(String registertype) {
        this.registertype = registertype;
    }
    public String getPriorholder() {
        return priorholder;
    }
    public void setPriorholder(String priorholder) {
        this.priorholder = priorholder;
    }
    public String getHolder() {
        return holder;
    }
    public void setHolder(String holder) {
        this.holder = holder;
    }
    public String getRecflag() {
        return recflag;
    }
    public void setRecflag(String recflag) {
        this.recflag = recflag;
    }
    public String getBanendrsmtmk() {
        return banendrsmtmk;
    }
    public void setBanendrsmtmk(String banendrsmtmk) {
        this.banendrsmtmk = banendrsmtmk;
    }
    public String getBillstatus() {
        return billstatus;
    }
    public void setBillstatus(String billstatus) {
        this.billstatus = billstatus;
    }
    public String getHolderbankno() {
        return holderbankno;
    }
    public void setHolderbankno(String holderbankno) {
        this.holderbankno = holderbankno;
    }
    public String getHolderbankaccount() {
        return holderbankaccount;
    }
    public void setHolderbankaccount(String holderbankaccount) {
        this.holderbankaccount = holderbankaccount;
    }
    public String getOwnerbankno() {
        return ownerbankno;
    }
    public void setOwnerbankno(String ownerbankno) {
        this.ownerbankno = ownerbankno;
    }
    public String getOwnerbankaccount() {
        return ownerbankaccount;
    }
    public void setOwnerbankaccount(String ownerbankaccount) {
        this.ownerbankaccount = ownerbankaccount;
    }
    public String getPaybillway() {
        return paybillway;
    }
    public void setPaybillway(String paybillway) {
        this.paybillway = paybillway;
    }
    public String getSourceappno() {
        return sourceappno;
    }
    public void setSourceappno(String sourceappno) {
        this.sourceappno = sourceappno;
    }
    public Integer getSourceid() {
        return sourceid;
    }
    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }
    public String getBankbillsysno() {
        return bankbillsysno;
    }
    public void setBankbillsysno(String bankbillsysno) {
        this.bankbillsysno = bankbillsysno;
    }
    public Integer getLasttxid() {
        return lasttxid;
    }
    public void setLasttxid(Integer lasttxid) {
        this.lasttxid = lasttxid;
    }
    public Integer getProcessflag() {
        return processflag;
    }
    public void setProcessflag(Integer processflag) {
        this.processflag = processflag;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public Date getLastupdatetime() {
        return lastupdatetime;
    }
    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }
    public Integer getFlawflag() {
        return flawflag;
    }
    public void setFlawflag(Integer flawflag) {
        this.flawflag = flawflag;
    }
    public Double getSettledamount() {
        return settledamount;
    }
    public void setSettledamount(Double settledamount) {
        this.settledamount = settledamount;
    }
    public Double getDiscountregrate() {
        return discountregrate;
    }
    public void setDiscountregrate(Double discountregrate) {
        this.discountregrate = discountregrate;
    }
    public Integer getDaysinway() {
        return daysinway;
    }
    public void setDaysinway(Integer daysinway) {
        this.daysinway = daysinway;
    }
    public Integer getDeferdays() {
        return deferdays;
    }
    public void setDeferdays(Integer deferdays) {
        this.deferdays = deferdays;
    }
    public Integer getOrgwhid() {
        return orgwhid;
    }
    public void setOrgwhid(Integer orgwhid) {
        this.orgwhid = orgwhid;
    }
    
}
