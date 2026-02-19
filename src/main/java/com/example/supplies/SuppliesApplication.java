package com.example.supplies;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.supplies.entity.Item;
import com.example.supplies.service.ItemService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@MapperScan("com.example.supplies.repository") 
@RequiredArgsConstructor
public class SuppliesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuppliesApplication.class, args)
        .getBean(SuppliesApplication.class).exe();
    }

    /** DI */
    private final ItemService service;
    
    public void exe() {
        // ★全件検索
        System.out.println("=== 全件検索 ===");
        for (Item row : service.findAll()) {
            System.out.println(row);
        }
    }
}
    