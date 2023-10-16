package com.nexus.whc.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/*
 * TopRepository.java
 * 
 * TopRepositoryクラス
 */

/*
 * Repositoryクラス
 */

@Repository
public class TopDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public TopDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Map<String, Object>> searchAll() {

		// SQL文作成
		String sql = "SELECT * from s_filelink";

		// クエリを実行
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		// 取得したリストを返す
		return list;
	}

}
