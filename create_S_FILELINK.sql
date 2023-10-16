USE `whc`;

CREATE TABLE `s_filelink` (
	`seq_id` INT(11) NOT NULL COMMENT 'シーケンスID' AUTO_INCREMENT,									
	`publication_date` DATE NULL COMMENT '掲載日付',
	`title` VARCHAR(30) NULL COMMENT 'タイトル', 
	`content` VARCHAR(4096) NULL COMMENT '内容', 
	`delete_flg` BIT(1) NULL DEFAULT NULL COMMENT '削除フラグ',
	`created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'レコード作成日付',
	`created_user` VARCHAR(16) NULL DEFAULT NULL COMMENT 'レコード作成ユーザID' COLLATE 'utf8_general_ci',
	`updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'レコード最終更新日付',
	`updated_user` VARCHAR(16) NULL DEFAULT NULL COMMENT 'レコード最終更新ユーザID' COLLATE 'utf8_general_ci',
	PRIMARY KEY (`seq_id`) USING BTREE
)
COMMENT='ファイルリンク'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;