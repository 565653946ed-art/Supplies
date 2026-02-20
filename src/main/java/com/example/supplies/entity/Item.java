/*DBのitemテーブルのデータを扱うクラス
 * 
 */

package com.example.supplies.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Item {
	//商品ID
	private Integer id;
	//商品コード
	private String itemCode;
	//商品名
	private String name;
	//商品在庫
	private Integer stock;
	//商品在庫 確定待ちの使用数
	private Integer pendingUsage;

}
