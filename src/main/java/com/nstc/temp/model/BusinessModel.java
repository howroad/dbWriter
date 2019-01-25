package com.nstc.temp.model;

import java.util.Date;

/** 理财登记 */
public class BusinessModel {
    /** 业务ID */
    private Integer bussId;
    /** 业务品种 */
    private String bussType;
    /** 申请流水号 */
    private Integer applyId;
    /** 存单号 */
    private String bussNo;
    /** 性质 */
    private String nature;
    /** 单位 */
    private String cltno;
    /** 合作金融机构 */
    private Integer lsh;
    /** 币种 */
    private String curCode;
    /** 挂钩币种 */
    private String curCodeLink;
    /** 金额 */
    private Double amount;
    /** 余额 */
    private Double balance;
    /** 理财存入账户 */
    private String accountNo;
    /** 年利率 */
    private Double rate;
    /** 存入日 */
    private Date actDate;
    /** 起始日 */
    private Date startDate;
    /** 到期日 */
    private Date endDate;
    /** 观察日 */
    private Date watchDate;
    /** 计息天数(方式) */
    private Integer interestDay;
    /** 计息基数 */
    private Integer interestBase;
    /** 是否可以提前支取  */
    private Integer advFlag;
    /**  */
    private Integer adjustDay;
    /** 备注 */
    private String memo;
    /** 申购日 */
    private Date applyDate;
    /** 理财产品名称 */
    private String financeName;
    /** 产品代码 */
    private String financeNo;
    /** 计息天数 */
    private Integer expectDay;
    /** 预期收益率 */
    private Double expectRate;
    /** 同期基准利率 */
    private Double standRate;
    /** 加点 */
    private Double addPoint;
    /** 上浮执行利率 */
    private Double floatRate;
    /** 债券名称 */
    private String bondName;
    /** 债券代码 */
    private String bondNo;
    /** 债券数量 */
    private Integer bondNum;
    /** 付息期 */
    private String interestPeriod;
    /** 还本方式 */
    private String repayType;
    /** LIBRO参考值 */
    private String libroRefe;
    /** LIBRO利率值<= */
    private Double libroMax;
    /** LIBRO利率值> */
    private Double libroMin;
    /** 利率挂钩及期限 */
    private Integer rateLink;
    /** 父ID */
    private Integer pBussId;
    /** 驳回原因 */
    private String rejectReason;
    /** 结束原因 */
    private String reason;
    /** 创建人 */
    private String creator;
    /** 创建时间 */
    private Date createTime;
    /** 人民币金额 */
    private Double amountlocal;
    /** 挂钩汇率 */
    private Double linkRate;
    /** 债券面值 */
    private Double parValue;
    /** 存款限额 */
    private Double depositAmount;
    /** 结束方式 */
    private String finishType;
    /** 状态 */
    private Integer state;
    public Integer getBussId() {
        return bussId;
    }
    public void setBussId(Integer bussId) {
        this.bussId = bussId;
    }
    public String getBussType() {
        return bussType;
    }
    public void setBussType(String bussType) {
        this.bussType = bussType;
    }
    public Integer getApplyId() {
        return applyId;
    }
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }
    public String getBussNo() {
        return bussNo;
    }
    public void setBussNo(String bussNo) {
        this.bussNo = bussNo;
    }
    public String getNature() {
        return nature;
    }
    public void setNature(String nature) {
        this.nature = nature;
    }
    public String getCltno() {
        return cltno;
    }
    public void setCltno(String cltno) {
        this.cltno = cltno;
    }
    public Integer getLsh() {
        return lsh;
    }
    public void setLsh(Integer lsh) {
        this.lsh = lsh;
    }
    public String getCurCode() {
        return curCode;
    }
    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }
    public String getCurCodeLink() {
        return curCodeLink;
    }
    public void setCurCodeLink(String curCodeLink) {
        this.curCodeLink = curCodeLink;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public String getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public Double getRate() {
        return rate;
    }
    public void setRate(Double rate) {
        this.rate = rate;
    }
    public Date getActDate() {
        return actDate;
    }
    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Date getWatchDate() {
        return watchDate;
    }
    public void setWatchDate(Date watchDate) {
        this.watchDate = watchDate;
    }
    public Integer getInterestDay() {
        return interestDay;
    }
    public void setInterestDay(Integer interestDay) {
        this.interestDay = interestDay;
    }
    public Integer getInterestBase() {
        return interestBase;
    }
    public void setInterestBase(Integer interestBase) {
        this.interestBase = interestBase;
    }
    public Integer getAdvFlag() {
        return advFlag;
    }
    public void setAdvFlag(Integer advFlag) {
        this.advFlag = advFlag;
    }
    public Integer getAdjustDay() {
        return adjustDay;
    }
    public void setAdjustDay(Integer adjustDay) {
        this.adjustDay = adjustDay;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public Date getApplyDate() {
        return applyDate;
    }
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
    public String getFinanceName() {
        return financeName;
    }
    public void setFinanceName(String financeName) {
        this.financeName = financeName;
    }
    public String getFinanceNo() {
        return financeNo;
    }
    public void setFinanceNo(String financeNo) {
        this.financeNo = financeNo;
    }
    public Integer getExpectDay() {
        return expectDay;
    }
    public void setExpectDay(Integer expectDay) {
        this.expectDay = expectDay;
    }
    public Double getExpectRate() {
        return expectRate;
    }
    public void setExpectRate(Double expectRate) {
        this.expectRate = expectRate;
    }
    public Double getStandRate() {
        return standRate;
    }
    public void setStandRate(Double standRate) {
        this.standRate = standRate;
    }
    public Double getAddPoint() {
        return addPoint;
    }
    public void setAddPoint(Double addPoint) {
        this.addPoint = addPoint;
    }
    public Double getFloatRate() {
        return floatRate;
    }
    public void setFloatRate(Double floatRate) {
        this.floatRate = floatRate;
    }
    public String getBondName() {
        return bondName;
    }
    public void setBondName(String bondName) {
        this.bondName = bondName;
    }
    public String getBondNo() {
        return bondNo;
    }
    public void setBondNo(String bondNo) {
        this.bondNo = bondNo;
    }
    public Integer getBondNum() {
        return bondNum;
    }
    public void setBondNum(Integer bondNum) {
        this.bondNum = bondNum;
    }
    public String getInterestPeriod() {
        return interestPeriod;
    }
    public void setInterestPeriod(String interestPeriod) {
        this.interestPeriod = interestPeriod;
    }
    public String getRepayType() {
        return repayType;
    }
    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }
    public String getLibroRefe() {
        return libroRefe;
    }
    public void setLibroRefe(String libroRefe) {
        this.libroRefe = libroRefe;
    }
    public Double getLibroMax() {
        return libroMax;
    }
    public void setLibroMax(Double libroMax) {
        this.libroMax = libroMax;
    }
    public Double getLibroMin() {
        return libroMin;
    }
    public void setLibroMin(Double libroMin) {
        this.libroMin = libroMin;
    }
    public Integer getRateLink() {
        return rateLink;
    }
    public void setRateLink(Integer rateLink) {
        this.rateLink = rateLink;
    }
    public Integer getpBussId() {
        return pBussId;
    }
    public void setpBussId(Integer pBussId) {
        this.pBussId = pBussId;
    }
    public String getRejectReason() {
        return rejectReason;
    }
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Double getAmountlocal() {
        return amountlocal;
    }
    public void setAmountlocal(Double amountlocal) {
        this.amountlocal = amountlocal;
    }
    public Double getLinkRate() {
        return linkRate;
    }
    public void setLinkRate(Double linkRate) {
        this.linkRate = linkRate;
    }
    public Double getParValue() {
        return parValue;
    }
    public void setParValue(Double parValue) {
        this.parValue = parValue;
    }
    public Double getDepositAmount() {
        return depositAmount;
    }
    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }
    public String getFinishType() {
        return finishType;
    }
    public void setFinishType(String finishType) {
        this.finishType = finishType;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public String toString() {
        return "BusinessModel [bussId=" + bussId + ", bussType=" + bussType + ", applyId=" + applyId + ", bussNo="
                + bussNo + ", nature=" + nature + ", cltno=" + cltno + ", lsh=" + lsh + ", curCode=" + curCode
                + ", curCodeLink=" + curCodeLink + ", amount=" + amount + ", balance=" + balance + ", accountNo="
                + accountNo + ", rate=" + rate + ", actDate=" + actDate + ", startDate=" + startDate + ", endDate="
                + endDate + ", watchDate=" + watchDate + ", interestDay=" + interestDay + ", interestBase="
                + interestBase + ", advFlag=" + advFlag + ", adjustDay=" + adjustDay + ", memo=" + memo + ", applyDate="
                + applyDate + ", financeName=" + financeName + ", financeNo=" + financeNo + ", expectDay=" + expectDay
                + ", expectRate=" + expectRate + ", standRate=" + standRate + ", addPoint=" + addPoint + ", floatRate="
                + floatRate + ", bondName=" + bondName + ", bondNo=" + bondNo + ", bondNum=" + bondNum
                + ", interestPeriod=" + interestPeriod + ", repayType=" + repayType + ", libroRefe=" + libroRefe
                + ", libroMax=" + libroMax + ", libroMin=" + libroMin + ", rateLink=" + rateLink + ", pBussId="
                + pBussId + ", rejectReason=" + rejectReason + ", reason=" + reason + ", creator=" + creator
                + ", createTime=" + createTime + ", amountlocal=" + amountlocal + ", linkRate=" + linkRate
                + ", parValue=" + parValue + ", depositAmount=" + depositAmount + ", finishType=" + finishType
                + ", state=" + state + "]";
    }
    
}
