package kr.ac.sungkyul.gs25.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.UserVo;

@Repository
public class ManageDao {
	@Autowired
	private SqlSession sqlSession;

	public int getTotalCount() {
		return sqlSession.selectOne("manage.totalCount");
	}

	// 회원 리스트
	public List<UserVo> getList(int page, int pagesize, String keyword) {
		Map<String, Object> map = new HashMap<>();

		// 키보드가 null or 비어있을 때 리스트 가져오기
		if (keyword == null || "".equals(keyword)) {

			map.put("page_top", (page - 1) * pagesize + 1);
			map.put("page_bottom", page * pagesize);

			List<UserVo> list = sqlSession.selectList("manage.getList", map);
			return list;

			// 검색된 리스트 가져오기
		} else {
			map.put("keyword", "%" + keyword + "%");
			map.put("page_top", (page - 1) * pagesize + 1);
			map.put("page_bottom", page * pagesize);

			List<UserVo> list = sqlSession.selectList("manage.getListKeyword", map);
			return list;
		}
	}
}
