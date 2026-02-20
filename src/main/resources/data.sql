-- ユーザー初期データ
INSERT INTO users (username, password,role)
VALUES
  ('admin', '$2a$10$sxAxlH90e.mPRHkeO9GBNumyIDwv8yaXqeHzDgyY.eIGwklDCDncO',
  	'ROLE_ADMIN'),
  ('user', '$2a$10$4lYxEvBfj2XMzCkU/n5sV.6H0jdQ86x/PGFT8ea1Dv70wFAF.Ryz2',
  'ROLE_USER');

-- 備品初期データ
INSERT INTO items (name, stock,pending_usage)
VALUES
  ('ポーション', 5,0),
  ('ハイポーション', 10,0),
  ('エーテル', 7,0),
  ('ファニックスの尾', 3,0);
--管理コード初期設定
UPDATE items
SET item_code = CONCAT('ITM', LPAD(id, 3, '0'))
WHERE item_code IS NULL;
