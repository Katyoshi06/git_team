package com.nexus.whc.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nexus.whc.models.Client;

/*
 * LockRepository.java
 *
 * LockRepositoryクラス
 */

@Repository
public class LockDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//ロックテーブルにロック情報を挿入
	public int registLockUser(String seq_id, String name, String record_id, String user_id) {
		//SQL文
		String sql ="INSERT INTO s_lock (seq_id, locking_table_name, locking_record_id, locking_user_id) "
				+ "VALUES (?, ?, ?, ?)";

		//?の箇所を置換
		Object[] param = {seq_id,name,record_id,user_id};

		//クエリ実行
		int registLock = jdbcTemplate.update(sql,param);

		return registLock;
	}
/*	//ロック情報の確認はテーブル名が入るので各自のDaoに実装してください
	public Map<String,Object> lockUser(String table_name, String record_id){
		//SQL文
		String sql = "SELECT * FROM s_lock "
				+ "INNER JOIN m_user ON s_lock.locking_user_id = m_user.user_id "
				+ "INNER JOIN m_client ON s_lock.locking_record_id = m_client.client_id "
				+ "WHERE locking_table_name=? AND locking_record_id=?";

		//?の箇所を置換
		Object[] param = {table_name, record_id};

		//クエリ実行
		Map<String,Object> lockUser = jdbcTemplate.queryForMap(sql,param);

		return lockUser;
	}
*/
	//ロックテーブルのロック情報を削除（ロックの解除）
	public int deleteLockUser(String name, String record_id, String user_id) {
		//SQL文
		String sql ="DELETE FROM s_lock WHERE locking_table_name=? "
				+ "AND locking_record_id=? AND locking_user_id =?";

		//?の箇所を置換
		Object[] param = {name,record_id,user_id};

		//クエリ実行
		int deleteLock = jdbcTemplate.update(sql,param);

		return deleteLock;
	}


	//ログイン中のユーザを取得するmethod
	public String loginUser(String loginUser) {
		//SQL文
		String sql = "SELECT user_id FROM s_login WHERE user_id=?";

		//?の箇所を置換
		Object[] param = {loginUser};

		//クエリ実行
		String userId = jdbcTemplate.queryForObject(sql,param,String.class);

		return userId;
	}

}
