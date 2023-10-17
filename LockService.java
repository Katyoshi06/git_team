package com.nexus.whc.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.nexus.whc.repository.ClientDao;
import com.nexus.whc.repository.LockDao;

/*
 * LockService.java
 *
 * LockServiceクラス
 */
@Service
public class LockService {
	private final LockDao dao;

	@Autowired
	public LockService(LockDao dao) {
		this.dao = dao;
	}

	//ロックテーブルにロック情報を挿入
	public boolean registLockUser(String seq_id, String name, String record_id, String user_id) {
		boolean lock = true;
		try {
			dao.registLockUser(seq_id, name, record_id, user_id);
			lock = true;
		}catch(DuplicateKeyException e) {
			lock = false;
		}

		return lock;
	}
	//ロック情報の確認(DaoのlockUserはテーブル名が入るので各自のDaoに実装をお願いします)
	public Map<String,Object> lockUser(String table_name, String record_id){
		Map<String,Object> lockUserMap = null;
		try {
			lockUserMap = dao.lockUser(table_name,record_id);
		}catch(EmptyResultDataAccessException | NullPointerException e){
		}
		return lockUserMap;
	}

	//ロックテーブル情報削除
	public int deleteLockUser(String name, String record_id, String user_id) {
		int result =0;
		try {
			result += dao.deleteLockUser(name, record_id, user_id);
		}catch(EmptyResultDataAccessException | NullPointerException e) {
			result += 0;
		}
		return result;
	}

	//ログイン中のユーザーを取得
	public String loginUser(String loginUser){
		String userId = dao.loginUser(loginUser);
		return userId;
	}

}
