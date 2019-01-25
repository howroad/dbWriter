package com.nstc.temp.model;

import java.util.Date;

/** 此票据指特定人、特定时间持有的票据，一张票据多次持有形成多条记录 */
public class WarehouseBill {
    /**  */
    private Integer wbid;
    /**  */
    private Integer whid;
    /** 票证ID */
    private Integer blid;
    /** 持有开始日期 */
    private Date begindate;
    /** 持有到期日期 */
    private Date enddate;
    /** 锁定标记 */
    private Integer locked;
    /** 结束标记 */
    private Integer endflag;
    /**
     * R01 背书转让 R02 托收 R03 贴现 P01 解付
     */
    private String endtype;
    /**
     * 用于托收与贴现登记 0:未登记，1:已登记
     */
    private Integer registerflag;
    /** 登记时间 */
    private Date registerdate;
    /** 登记金额 */
    private Double registeramount;
    /** 结束原因 */
    private String registertype;
    /** 应收票据的前手背书人 */
    private String priorholder;
    /** 持有单位编号 */
    private String holder;
    /** 收付标记 */
    private String recflag;
    /** 是否可再转让 EM00:可再转让 EM01:不得转让 */
    private String banendrsmtmk;
    /** 票证状态 */
    private String billstatus;
    /** 持有人开户行行号 */
    private String holderbankno;
    /** 持有人账号 */
    private String holderbankaccount;
    /** 权利人开户行行号 */
    private String ownerbankno;
    /** 权利人账号 */
    private String ownerbankaccount;
    /** 出票方式 BILLS:质押 DEPOSIT:保证金 CREDIT:信用 MULTI:混合 */
    private String paybillway;
    /** 数据来源 */
    private String sourceappno;
    /** 数据来源主键 */
    private Integer sourceid;
    /** 银行唯一标识 */
    private String bankbillsysno;
    /** 末次交易ID */
    private Integer lasttxid;
    /** 处理标识（用于标记收付款系统是否已处理 0:未处理 1:已处理） */
    private Integer processflag;
    /** 备注 */
    private String remark;
    /** 记录创建时间 */
    private Date createtime;
    /** 末次更新时间 */
    private Date lastupdatetime;
    /** 瑕疵标识 */
    private Integer flawflag;
    /** 单据已结金额 */
    private Double settledamount;
    /** 贴现登记利率 */
    private Double discountregrate;
    /** 贴现在途天数 */
    private Integer daysinway;
    /** 贴现顺延天数 */
    private Integer deferdays;
    /** 原始库存 */
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
