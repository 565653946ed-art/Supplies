/*MyBatisを使いDBを扱う,XMLに渡す
 *Serviceクラスと連携しデータを扱う 
 */

package com.example.supplies.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.supplies.entity.Item;

@Mapper
public interface ItemMapper {
	//全件リスト
	List<Item>findAll();
	//IDから取得
	Item findById(Integer id);
	//在庫の更新
	void updateStock(Integer id,Integer stock);
	//備品の追加
	void insert(Item item);
	//備品の削除
	void deleteById(Integer id);
	//備品の購入
	void update(Item item);
	//使用保留中のアイテムを検索
	List<Item> findItemsWithPendingUsage();
	//使用の仮記録
	void updatePendingUsage(
			@Param("itemId") Integer itemId,
			@Param("usage") Integer usage);
	//使用数確定
	void confirmUsage(
	        @Param("itemId") Integer itemId,
	        @Param("newStock") Integer newStock);
	//使用数の仮記録リセット
	void resetAllPendingUsage();
	//itemcode用
	void updateItemCode(@Param("id") Integer id,
            @Param("itemCode") String itemCode);
	//同じ名前はないか判断
	boolean existsByName(String name);
	

}
