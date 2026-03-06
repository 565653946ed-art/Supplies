/*
 * itemに関する処理をまとめたサービス
 */


package com.example.supplies.service;

import java.util.List;

import com.example.supplies.entity.Item;

public interface ItemService {
	//全リスト
	List<Item>findAll();
	//備品追加
	void addItem(Item item);
	//備品削除
	void deleteItem(Integer id);
	//注文
	void orderItem(Integer id,Integer quantity);
	//使用保留中のアイテムを検索
	List<Item> findItemsWithPendingUsage();
	//使用数仮記録
	void savePendingUsage(Integer itemId,Integer usage);
	//使用数確定
	void confirmAllPendingUsage();
	//使用数仮記録リセット
	void resetAllPendingUsage();
	//発注点
	void updateReorderPoint(Integer id, Integer reorderPoint);
}
