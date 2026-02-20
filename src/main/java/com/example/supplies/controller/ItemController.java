package com.example.supplies.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.supplies.entity.Item;
import com.example.supplies.security.LoginUser;
import com.example.supplies.service.ItemService;
import com.example.supplies.service.StockHistoryService;

import lombok.RequiredArgsConstructor;

//Interceptorでログインチェック

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class ItemController {
	private final ItemService itemService;
	private final StockHistoryService historyService;

	// 商品一覧 + ログインユーザー
	@GetMapping
	public String list(
			@AuthenticationPrincipal LoginUser loginUser,
			Model model) {

		// 商品一覧をDBから取得
		model.addAttribute("items", itemService.findAll());

		// Spring Security が管理しているログインユーザー
		model.addAttribute("loginUser", loginUser.getUser());

		return "menu";
	}

	//商品追加画面表示
	@GetMapping("/add")
	public String addForm() {
		return "item-add";
	}

	//商品登録
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add")
	public String add(@ModelAttribute Item item,
			Model model) {

		try {
			itemService.addItem(item);
			return "redirect:/menu";

		} catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
			model.addAttribute("item", item);
			return "item-add"; // 入力画面に戻す
		}
	}

	//製品削除
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, HttpSession session) {
		itemService.deleteItem(id);
		return "redirect:/menu";

	}

	//注文確定
	@PostMapping("/order/confirm")
	public String orderConfirm(Integer itemId, Integer quantity) {

		itemService.orderItem(itemId, quantity);

		return "redirect:/menu";
	}

	//使用数を一時保存
	@PostMapping("/usage/pending")
	public String savePendingUsage(Integer itemId, Integer usage) {
		itemService.savePendingUsage(itemId, usage);
		return "redirect:/menu";

	}

	//使用を確定
	@PostMapping("/usage/confirm-all")
	public String confirmAllUsage() {
		itemService.confirmAllPendingUsage();
		return "redirect:/menu";
	}

	//使用数をリセット
	@PostMapping("/usage/reset-all")
	public String resetAllUsage() {
		itemService.resetAllPendingUsage();
		return "redirect:/menu";
	}

}
