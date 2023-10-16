package com.nexus.whc.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.whc.models.Filelink;
import com.nexus.whc.repository.TopDao;

/*
 * TopService.java
 * 
 * TopServiceクラス
 */

/*
 * Serviceクラス
 */

@Service
public class TopService {
	private final TopDao topDao;

	@Autowired
	public TopService(TopDao topDao) {
		this.topDao = topDao;
	}
	
	public List<Filelink> getAllFiles() {

		//return用list
		List<Filelink> list = new ArrayList<Filelink>();
		//JDBCテンプレートから出力した全件取得List
		List<Map<String, Object>> beforeList = topDao.searchAll();

		//全件抽出したlistの要素数分だけループ。
		for (Map<String, Object> map : beforeList) {

			//List代入用Filelink
			Filelink filelink = new Filelink();

			//テーブルのカラム数分だけループ
			for (String key : map.keySet()) {

				try {

					//switch文でMapの中身をFilelinkへ詰め替えをおこなう。
					switch (key) {

					case "seq_id":
						int seqId = (int) map.get(key);
						filelink.setSeqId(seqId);
						break;

					case "publication_date":
						Date publicationDate = (Date) map.get(key);
						filelink.setPublicationDate(publicationDate);
						break;

					case "title":
						String title = map.get(key).toString();
						filelink.setTitle(title);
						break;

					case "content":
						String content = map.get(key).toString();
						filelink.setContent(content);
						break;

					case "delete_flg":
						filelink.setDeleteFlg((boolean) map.get(key));
						break;

					case "created_at":
						Timestamp createdAtTimestamp = (Timestamp) map.get(key);
						Date createdAt = new Date(createdAtTimestamp.getTime());
						filelink.setCreatedAt(createdAt);
						break;

					case "created_user":
						String createdUser = map.get(key).toString();
						filelink.setCreatedUser(createdUser);
						break;

					case "updated_at":
						Timestamp updatedAtTimestamp = (Timestamp) map.get(key);
						Date updatedAt = new Date(updatedAtTimestamp.getTime());
						filelink.setUpdatedAt(updatedAt);
						break;

					case "updated_user":
						String updatedUser = map.get(key).toString();
						filelink.setUpdatedUser(updatedUser);
						break;

					}

				} catch (NullPointerException e) {
					//nullを検知したらこのループを抜けるためだけのコード。なので処理なし。
				}
			}

			//return用Listに追加
			list.add(filelink);

		}

		return list;
	}

}
