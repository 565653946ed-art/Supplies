//履歴用Mapper

package com.example.supplies.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.supplies.entity.StockHistory;
@Mapper
public interface StockHistoryMapper {
	  // 履歴登録
    void insertHistory(StockHistory history);

    // 日付の降順で全件取得
    List<StockHistory> findAllOrderByDateDesc();
}
