package com.trablock.domain;

import java.time.LocalDateTime;

public class Memo {
    private long id;                // 메모 아이디
    private long partyId;           // 모임 아이디
    private LocalDateTime date;     // 메모 날짜(생성? OR 마지막으로 수정된?)
    private String description;     // 메모 내용
    private boolean isChecklist;    // 체크리스트여부

    public Memo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPartyId() {
        return partyId;
    }

    public void setPartyId(long partyId) {
        this.partyId = partyId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChecklist() {
        return isChecklist;
    }

    public void setChecklist(boolean checklist) {
        isChecklist = checklist;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "id=" + id +
                ", partyId=" + partyId +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", isChecklist=" + isChecklist +
                '}';
    }
}
