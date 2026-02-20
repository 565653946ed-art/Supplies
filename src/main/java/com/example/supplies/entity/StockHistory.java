package com.example.supplies.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StockHistory {
	
	private Integer historyId;
	private Integer itemId;
	private String itemCode;
	private String itemName;
	private String actionType;
	private Integer quantity;
	private String username;
	private LocalDateTime actionDate;

}
