
-- 既存テーブルがあれば削除
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS stock_history;

-- ユーザー（ログイン用）
CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  role VARCHAR(20) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;--日本語と絵文字が安全に使える文字コード

-- 備品テーブル
CREATE TABLE items (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  stock INT NOT NULL,
  pending_usage INT NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--使用注文履歴
CREATE TABLE stock_history(
    history_id INT AUTO_INCREMENT PRIMARY KEY,
    item_id INT,
    item_name VARCHAR(100) NOT NULL,
    action_type VARCHAR(50),
    quantity INT,
    username VARCHAR(100),
    action_date DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

