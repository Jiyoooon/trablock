package com.trablock.domain;

import java.time.LocalDateTime;

public class Post {
    private long id;                    // 게시물아이디
    private long id2;                   // 유저아이디
    private long id3;                   // 모임아이디
    private String title;               // 제목
    private String description;         // 내용
    private LocalDateTime created;      // 생성날짜

    public Post() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId2() {
        return id2;
    }

    public void setId2(long id2) {
        this.id2 = id2;
    }

    public long getId3() {
        return id3;
    }

    public void setId3(long id3) {
        this.id3 = id3;
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", id2=" + id2 +
                ", id3=" + id3 +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                '}';
    }
}
