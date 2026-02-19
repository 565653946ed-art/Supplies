/*履歴処理サービスの実装
 * 
 */
package com.example.supplies.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.supplies.service.StockHistoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StockHistoryController {
	private final StockHistoryService stockHistoryService;
	//履歴一覧表示
	@GetMapping("/history")
	public String showHistory(Model model) {
		model.addAttribute("historyList",
				stockHistoryService.getAllHistory());
		Authentication auth =
			    SecurityContextHolder.getContext().getAuthentication();

		model.addAttribute("loginUserName",auth.getName());
		return "history";
	}
	

}
