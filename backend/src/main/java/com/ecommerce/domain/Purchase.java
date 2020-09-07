package com.ecommerce.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Purchase {

    private long id;
    private long purchaseId; // purchase Id get from contract
    private String state = PurchaseState.I.toString(); // I(Initial-purchased), P(Paid), S(sent), C(confirmed), X(cancelled)
    private long sellerId;
    private long buyerId;
    private long itemId;
    private LocalDateTime createdAt;
    private String contractAddress;

    
    
    public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getPurchaseId() {
		return purchaseId;
	}



	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public long getSellerId() {
		return sellerId;
	}



	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}



	public long getBuyerId() {
		return buyerId;
	}



	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}



	public long getItemId() {
		return itemId;
	}



	public void setItemId(long itemId) {
		this.itemId = itemId;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public String getContractAddress() {
		return contractAddress;
	}



	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}



	@Override
    public String toString()
    {
        return "{ id: " + id +
                "\n\tpurchaseId: " + purchaseId +
                "\n\tstate: " + state +
                "\n\tsellerId: " + sellerId +
                "\n\tbuyerId: " + buyerId +
                "\n\titemId: " + itemId +
                "\n\tcreatedAt: " + createdAt +
                "\n\tcontractAddress: " + contractAddress +
                " }";
    }

}
