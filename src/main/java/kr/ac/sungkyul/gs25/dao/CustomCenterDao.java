package kr.ac.sungkyul.gs25.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.AttachFileVO;
import kr.ac.sungkyul.gs25.vo.CustomBoardVo;

/* 2016-10-01 작업자 : 최형민 
개발 상황 : 완료 */ 

@Repository
public class CustomCenterDao {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	//게시판 리스트
	public List<CustomBoardVo> getList(int page, int pagesize,String keyword) {
		Map<String, Object> map=new HashMap<>();

		//키보드가 null or 비어있을 때 리스트 가져오기
		if (keyword == null || "".equals(keyword)) {
			map.put("page_start", (page - 1) * pagesize + 1);
			map.put("page_end", page * pagesize);
			
			List<CustomBoardVo> list=sqlSession.selectList("board.getList",map);
			return list;
			
		//검색된 리스트 가져오기
		} else {
			map.put("keyword", "%" + keyword + "%");
			map.put("page_start", (page - 1) * pagesize + 1);
			map.put("page_end", page * pagesize);
			
			List<CustomBoardVo> list=sqlSession.selectList("board.getListKeyword",map);
			return list;
		}
		
	}
	//게시판 총 개수
	public int getTotalCount() {
		return sqlSession.selectOne("board.totalCount");
	}
	
	//게시판 글쓰기
	public Long insert(CustomBoardVo vo) { // 처음 삽입
		sqlSession.insert("board.insertBoard", vo);
		return vo.getNo();
	}
	
	public Long reply(CustomBoardVo vo) { // 답글 삽입
		sqlSession.insert("board.replyBoard", vo);
		return vo.getNo();
	}

	public void delete(int no, int orderno) { // 첨부파일 삭제
		Map<String, Object> map=new HashMap<>();
		map.put("no", no);
		map.put("orderno", orderno);
		
		sqlSession.delete("board.deleteAttachFile",map);
	}
	
	//게시판 삭제
	public void delete(CustomBoardVo vo) {
		sqlSession.delete("board.deleteBoard",vo);
	}
	
	//게시판 정보 얻기
	public CustomBoardVo get(Long no1) { // 외래키를 가지고 게시판의 정보를 가져와
		CustomBoardVo vo=sqlSession.selectOne("board.listByNo",no1);
		return vo;
	}
	
	//조회수 증가
	public void updateViewCount(Long no) { 
		sqlSession.update("board.updateViewCount",no);
	}
	
	//게시판 수정
	public void update(CustomBoardVo vo) {

		sqlSession.update("board.update",vo);
	}
	
	//그룹 내 순서 정렬
	public void updatereplyCount(int groupNo, int orderNo) { // 답글의 그룹 번호와 그룹 내 번호 설정
		Map<String, Object> map=new HashMap<>();
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);
		
		sqlSession.update("board.updatereplyCount",map);
	}
	
	//첨부파일 정보 얻기
	public AttachFileVO selectAttachFileByFNO(Long fNO) { // (download)
		return sqlSession.selectOne("board.selectAttachFileByFNO", fNO);
	}
	
	//첨부파일 삽입
	public void insertAttachFile(AttachFileVO attachFileVO) {
		sqlSession.insert("board.insertAttachFile", attachFileVO);
	}


	public AttachFileVO selectAttachFileByNO(Long no) { // 게시판 해당 번호의 파일 정보(view)
		return sqlSession.selectOne("board.selectAttachFileByNO", no);
	}
	
	//첨부파일 그룹번호 반환
	public int getgroupno(AttachFileVO vo) { 
		sqlSession.selectList("board.list");
		return vo.getGroupno();
	}
	
	//사용자에게 댓글 달린 것을 확인 하기 위한 객체 얻기
	public CustomBoardVo getList(int groupNo) { // 해당 게시물의 사용자번호
		CustomBoardVo vo=sqlSession.selectOne("board.ListByGroupNo",groupNo);
		return vo;
	}
	
}



