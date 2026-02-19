package com.example.supplies.service;

import java.util.List;

import com.example.supplies.entity.StockHistory;

public interface StockHistoryService {
	

    // 履歴保存
    void saveHistory(Integer itemId,
                     String itemName,
                     String actionType,
                     Integer quantity);

    // 全履歴取得（日付降順）
    List<StockHistory> getAllHistory();

}
