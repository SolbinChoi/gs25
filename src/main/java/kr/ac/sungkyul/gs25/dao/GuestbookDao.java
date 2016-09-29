package kr.ac.sungkyul.gs25.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	@Autowired
	private DataSource dataSource; // 인터페이스
	@Autowired
	private SqlSession sqlSession;
	public int getCal(){
		int totalCount = sqlSession.selectOne("guestbook.getCal");
		return totalCount;
	}
	
	public List<GuestbookVo> getList(Long page,int pagesize, Long store_no){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page_top", (page-1)*pagesize + 1);
		map.put("page_bottom", page * pagesize);
		map.put("store_no", store_no);
		
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList", map);
		return list;
	}
	
	public void insert(GuestbookVo vo){
		sqlSession.insert("guestbook.insert", vo);
	}
	
	public void delete(GuestbookVo vo){
		sqlSession.delete("guestbook.delete",vo);
	}
	
}
