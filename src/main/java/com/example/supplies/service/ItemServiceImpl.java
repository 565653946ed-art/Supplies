/*
 * itemに関する処理をまとめたサービスの実装クラス
 *履歴に登録もしている
 */

package com.example.supplies.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.supplies.entity.Item;
import com.example.supplies.repository.ItemMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class ItemServiceImpl implements ItemService {
	private final ItemMapper itemMapper;
	private final StockHistoryService stockHistoryService;

	@Override
	public List<Item> findAll() {

		return itemMapper.findAll();
	}

	@Override
	public void addItem(Item item) {
		//item登録
		itemMapper.insert(item);

		//履歴登録
		stockHistoryService.saveHistory(
				item.getId(), //itemId
				item.getName(), //itemName
				"追加", //action
				item.getStock() //quantity
		);

	}

	@Override
	public void deleteItem(Integer id) {
		//削除前にitem情報取得
		Item item = itemMapper.findById(id);
		//item削除
		itemMapper.deleteById(id);
		//履歴登録
		stockHistoryService.saveHistory(
				item.getId(), //itemId        //itemId
				item.getName(), //itemName
				"削除", //action
				item.getStock() //quantity
		);

	}

	@Override
	public void orderItem(Integer id, Integer quantity) {
		Item item = itemMapper.findById(id);
		if (quantity <= 0) {
			return;
		}
		item.setStock(item.getStock() + quantity);
		itemMapper.update(item);
		//履歴保存
		stockHistoryService.saveHistory(
				item.getId(), //itemId
				item.getName(), //itemName
				"注文", //action
				quantity);

	}

	//使用数仮記録
	@Override
	public void savePendingUsage(Integer itemId, Integer usage) {
		itemMapper.updatePendingUsage(itemId, usage);
	}

	//使用数確定
	@Override
	public void confirmAllPendingUsage() {
		List<Item> items = itemMapper.findItemsWithPendingUsage();
		// 確定処理
		for (Item item : items) {
			int newStock = item.getStock() - item.getPendingUsage();
			itemMapper.confirmUsage(item.getId(), newStock);

			//履歴用
			stockHistoryService.saveHistory(
					item.getId(),
					item.getName(),
					"使用",
					item.getPendingUsage());

		}
	}

	//使用数仮記録リセット
	@Override
	public void resetAllPendingUsage() {
		itemMapper.resetAllPendingUsage();
	}
	//仮使用数が入力されていてまだ確定されていないitem一覧
	@Override
	public List<Item> findItemsWithPendingUsage() {

		return itemMapper.findItemsWithPendingUsage();
	}

}
