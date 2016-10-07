package kr.ac.sungkyul.gs25.dao;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.TestVo;

@Repository
public class TestDao {
	@Autowired
	private DataSource dataSource; // 인터페이스
	@Autowired
	private SqlSession sqlSession;

	public void insert(TestVo testvo){
		sqlSession.insert("test.insert", testvo);
	}
}
