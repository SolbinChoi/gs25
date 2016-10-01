package kr.ac.sungkyul.gs25.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.AttachFileEvVO;
import kr.ac.sungkyul.gs25.vo.EventBoardVo;

@Repository
public class EventBoardDao {
	@Autowired
	private DataSource dataSource; // 인터페이스
	@Autowired
	private SqlSession sqlSession;
	
	public int getCal(){
		int totalCount = sqlSession.selectOne("eventboard.getCal");
		return totalCount;
	}
	public List<EventBoardVo> getList(Long page,int pagesize, String keyword){
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(keyword ==null || "".equals(keyword)){
		map.put("page_top", (page-1)*pagesize + 1);
		map.put("page_bottom", page * pagesize);
		}else{
			map.put("keyword", "%"+keyword+"%");
			map.put("page_top", (page-1)*pagesize + 1);
			map.put("page_bottom", page * pagesize);
			
		}
		List<EventBoardVo> list = sqlSession.selectList("eventboard.getList", map); // no, image, title, count
		return list;
	}
	
	public EventBoardVo view(Long no){
		EventBoardVo vo = sqlSession.selectOne("eventboard.selectBoard", no);
		return vo;
	}
	public void updateViewCount(Long no){
		sqlSession.update("eventboard.updateCount", no);
	}
	public void delete(Long no){
		sqlSession.delete("eventboard.deleteFile", no);
	}
	public void delete(EventBoardVo vo){
		sqlSession.delete("eventboard.deleteVo", vo);
	}
	public Long insert(EventBoardVo vo){ // 이벤트 게시글 : 번호, 제목, 날짜 삽입
		sqlSession.insert("eventboard.insert", vo);
		return vo.getNo();
	}
	
	public void insertAttachEvFile(AttachFileEvVO attachFileEvVO){
		sqlSession.insert("eventboard.insertAttachEvFile", attachFileEvVO);
	}
}
