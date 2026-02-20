/*履歴処理サービスの実装
 * 
 */

package com.example.supplies.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.supplies.entity.StockHistory;
import com.example.supplies.repository.StockHistoryMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class StockHistoryServiceImpl implements StockHistoryService {
	private final StockHistoryMapper historyMapper;

    // 履歴保存
    @Override
    public void saveHistory(Integer itemId,
    						String itemCode,
                            String itemName,
                            String actionType,
                            Integer quantity) {

        StockHistory history = new StockHistory();
        history.setItemId(itemId);
        history.setItemCode(itemCode); 
        history.setItemName(itemName);
        history.setActionType(actionType);
        history.setQuantity(quantity);

        // ログインユーザー取得
        history.setUsername(
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName()
        );

        history.setActionDate(LocalDateTime.now());

        historyMapper.insertHistory(history);
    }

    // 全履歴取得（日付降順）
    @Override
    public List<StockHistory> getAllHistory() {
        return historyMapper.findAllOrderByDateDesc();
    }
}