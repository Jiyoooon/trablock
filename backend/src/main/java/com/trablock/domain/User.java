package com.trablock.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class User {
    private long id;
    private String nickname;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    
    private List<Wallet> wallets;
    private List<Party> parties;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public List<Wallet> getWallets() {
		return wallets;
	}
	public void setWallets(List<Wallet> wallets) {
		this.wallets = wallets;
	}
	public List<Party> getParties() {
		return parties;
	}
	public void setParties(List<Party> parties) {
		this.parties = parties;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", email=" + email + ", password=" + password + ", createdAt="
				+ createdAt + ", wallets=" + wallets + ", parties=" + parties + "]";
	}
    
    
}
