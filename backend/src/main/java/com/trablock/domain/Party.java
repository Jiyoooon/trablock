package com.trablock.domain;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Party {
    private long id;                    // 모임 아이디
    private String name;                // 모임 명
    private String explanation;         // 모임 설명
    private String created;      // 모임 생성날짜(기본 : 현재 시간)
    private BigDecimal target;          // 모임 목표금액
    private BigDecimal total_amount;     // 모임 실제금액
    private boolean payCycle;           // 모임 납입주기(false : 월단위, true : 주단위)
    private int payDate;                // 모임 납입날짜(월단위시 일을 의미하고, 주단위시 1=월요일 ~ 7=일요일을 의미)
    private BigDecimal payAmount;       // 모임 납입금
    private String image;               // 사진
    private String startDate;    // 여행 시작 날짜(기본)
    private String endDate;      // 여행 종료 날짜(기본)
    private String destination;         // 여행지
    private boolean available;          // 완료 여부(기본 : false)
    private BigDecimal exitFee;         // 퇴출수수료

    private boolean withdraw;				// 출금 신청 여부(누군가 신청하면 true로 바꿈)
    private String withdrawName;			// 누가 출금 신청했는지
    private BigDecimal withdrawAmount;		// 출금 양
    private String privatekey;
    
    private List<Long> members;
    
    private List<PartyMember> memberlist;

    private String finished;			// 모임통장 모금 종료
    private boolean type;				// 모임의 종류(여행이면 true, 아니면 false)

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

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public BigDecimal getTarget() {
		return target;
	}

	public void setTarget(BigDecimal target) {
		this.target = target;
	}

	public BigDecimal getTotalAmount() {
		return total_amount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.total_amount = totalAmount;
	}

	public boolean isPayCycle() {
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
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

	public boolean isWithdraw() {
		return withdraw;
	}

	public void setWithdraw(boolean withdraw) {
		this.withdraw = withdraw;
	}

	public String getWithdrawName() {
		return withdrawName;
	}

	public void setWithdrawName(String withdrawName) {
		this.withdrawName = withdrawName;
	}

	public BigDecimal getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(BigDecimal withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}

	public List<Long> getMembers() {
		return members;
	}

	public void setMembers(List<Long> members) {
		this.members = members;
	}

	public List<PartyMember> getMemberlist() {
		return memberlist;
	}

	public void setMemberlist(List<PartyMember> memberlist) {
		this.memberlist = memberlist;
	}

	public String getFinished() {
		return finished;
	}

	public void setFinished(String finished) {
		this.finished = finished;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Party{" +
				"id=" + id +
				", name='" + name + '\'' +
				", explanation='" + explanation + '\'' +
				", created='" + created + '\'' +
				", target=" + target +
				", totalAmount=" + total_amount +
				", payCycle=" + payCycle +
				", payDate=" + payDate +
				", payAmount=" + payAmount +
				", image='" + image + '\'' +
				", startDate='" + startDate + '\'' +
				", endDate='" + endDate + '\'' +
				", destination='" + destination + '\'' +
				", available=" + available +
				", exitFee=" + exitFee +
				", withdraw=" + withdraw +
				", withdrawName='" + withdrawName + '\'' +
				", withdrawAmount=" + withdrawAmount +
				", privatekey='" + privatekey + '\'' +
				", members=" + members +
				", memberlist=" + memberlist +
				", finished='" + finished + '\'' +
				", type=" + type +
				'}';
	}

	public Party() {
    	members = new LinkedList<>();
    }
}
