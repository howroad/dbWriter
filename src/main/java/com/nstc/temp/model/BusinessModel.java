package com.nstc.temp.model;

import java.util.Date;

/** ��ƵǼ� */
public class BusinessModel {
    /** ҵ��ID */
    private Integer bussId;
    /** ҵ��Ʒ�� */
    private String bussType;
    /** ������ˮ�� */
    private Integer applyId;
    /** �浥�� */
    private String bussNo;
    /** ���� */
    private String nature;
    /** ��λ */
    private String cltno;
    /** �������ڻ��� */
    private Integer lsh;
    /** ���� */
    private String curCode;
    /** �ҹ����� */
    private String curCodeLink;
    /** ��� */
    private Double amount;
    /** ��� */
    private Double balance;
    /** ��ƴ����˻� */
    private String accountNo;
    /** ������ */
    private Double rate;
    /** ������ */
    private Date actDate;
    /** ��ʼ�� */
    private Date startDate;
    /** ������ */
    private Date endDate;
    /** �۲��� */
    private Date watchDate;
    /** ��Ϣ����(��ʽ) */
    private Integer interestDay;
    /** ��Ϣ���� */
    private Integer interestBase;
    /** �Ƿ������ǰ֧ȡ  */
    private Integer advFlag;
    /**  */
    private Integer adjustDay;
    /** ��ע */
    private String memo;
    /** �깺�� */
    private Date applyDate;
    /** ��Ʋ�Ʒ���� */
    private String financeName;
    /** ��Ʒ���� */
    private String financeNo;
    /** ��Ϣ���� */
    private Integer expectDay;
    /** Ԥ�������� */
    private Double expectRate;
    /** ͬ�ڻ�׼���� */
    private Double standRate;
    /** �ӵ� */
    private Double addPoint;
    /** �ϸ�ִ������ */
    private Double floatRate;
    /** ծȯ���� */
    private String bondName;
    /** ծȯ���� */
    private String bondNo;
    /** ծȯ���� */
    private Integer bondNum;
    /** ��Ϣ�� */
    private String interestPeriod;
    /** ������ʽ */
    private String repayType;
    /** LIBRO�ο�ֵ */
    private String libroRefe;
    /** LIBRO����ֵ<= */
    private Double libroMax;
    /** LIBRO����ֵ> */
    private Double libroMin;
    /** ���ʹҹ������� */
    private Integer rateLink;
    /** ��ID */
    private Integer pBussId;
    /** ����ԭ�� */
    private String rejectReason;
    /** ����ԭ�� */
    private String reason;
    /** ������ */
    private String creator;
    /** ����ʱ�� */
    private Date createTime;
    /** ����ҽ�� */
    private Double amountlocal;
    /** �ҹ����� */
    private Double linkRate;
    /** ծȯ��ֵ */
    private Double parValue;
    /** ����޶� */
    private Double depositAmount;
    /** ������ʽ */
    private String finishType;
    /** ״̬ */
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
