package com.ecommerce.domain;

import java.time.LocalDateTime;

public class Comment {
    private long id;                // 댓글 아이디
    private long userId;            // 유저 아이디
    private long partyId;           // 모임 아이디
    private String description;     // 내용
    private LocalDateTime created;  // 생성날짜

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPartyId() {
        return partyId;
    }

    public void setPartyId(long partyId) {
        this.partyId = partyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", partyId=" + partyId +
                ", description='" + description + '\'' +
                ", created=" + created +
                '}';
    }
}
