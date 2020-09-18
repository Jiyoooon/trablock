package com.trablock.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Party {
    private long id;                    // 모임 아이디
    private String name;                // 모임 명
    private String explanation;         // 모임 설명
    private LocalDateTime created;      // 모임 생성날짜(기본 : 현재 시간)
    private BigDecimal target;          // 모임 목표금액
    private BigDecimal totalAmount;     // 모임 실제금액
    private boolean payCycle;           // 모임 납입주기(false : 월단위, true : 주단위)
    private int payDate;                // 모임 납입날짜(월단위시 일을 의미하고, 주단위시 1=월요일 ~ 7=일요일을 의미)
    private BigDecimal payAmount;       // 모임 납입금
    private String image;               // 사진
    private LocalDateTime startDate;    // 여행 시작 날짜(기본)
    private LocalDateTime endDate;      // 여행 종료 날짜(기본)
    private String destination;         // 여행지
    private boolean available;          // 완료 여부(기본 : false)
    private BigDecimal exitFee;         // 퇴출수수료

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public BigDecimal getTarget() {
        return target;
    }

    public void setTarget(BigDecimal target) {
        this.target = target;
    }

    public boolean getPayCycle() {
        return payCycle;
    }

    public void setPayCycle(boolean payCycle) {
        this.payCycle = payCycle;
    }

    public int getPayDate() {
        return payDate;
    }

    public void setPayDate(int payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public BigDecimal getExitFee() {
        return exitFee;
    }

    public void setExitFee(BigDecimal exitFee) {
        this.exitFee = exitFee;
    }

    @Override
    public String toString() {
        return "Party{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", explanation='" + explanation + '\'' +
                ", created=" + created +
                ", target=" + target +
                ", payCycle=" + payCycle +
                ", payDate=" + payDate +
                ", payAmount=" + payAmount +
                ", image='" + image + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", destination='" + destination + '\'' +
                ", available=" + available +
                ", exitFee=" + exitFee +
                '}';
    }

    public Party() { }
}
