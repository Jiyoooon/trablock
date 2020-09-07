package com.ecommerce.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Item
{
	private long id;
	private String name;
	private String category;
	private String explanation;
	private Boolean available = true;
	private long seller;
	private LocalDateTime registeredAt;
	private String image;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public long getSeller() {
		return seller;
	}

	public void setSeller(long seller) {
		this.seller = seller;
	}

	public LocalDateTime getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(LocalDateTime registeredAt) {
		this.registeredAt = registeredAt;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString()
	{
		return "{ id: " + id +
				"\n\tname: " + name +
				"\n\texplanation: " + explanation +
				"\n\tavailable: " + available +
				"\n\tseller: " + seller +
				"\n\tregisteredAt: " + registeredAt +
				"\n\timage: " + image +
				" }";
	}
}
