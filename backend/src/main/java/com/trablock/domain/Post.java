package com.trablock.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Post {
    private long id;                    // 게시물아이디
    private long userId;                   // 유저아이디
    private long partyId;                   // 모임아이디
    private String title;               // 제목
    private String description;         // 내용
    private LocalDateTime created;      // 생성날짜
    
    private BigDecimal target;          // 기존 목표금액
    private BigDecimal usedPrice;		// 실제 사용금액
    private String destination;			// 여행지

    public Post() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    

    public BigDecimal getTarget() {
		return target;
	}

	public void setTarget(BigDecimal target) {
		this.target = target;
	}

	public BigDecimal getUsedPrice() {
		return usedPrice;
	}

	public void setUsedPrice(BigDecimal usedPrice) {
		this.usedPrice = usedPrice;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", userId=" + userId + ", partyId=" + partyId + ", title=" + title + ", description=" + description
				+ ", created=" + created + ", target=" + target + ", usedPrice=" + usedPrice + ", destination="
				+ destination + "]";
	}

	
}
