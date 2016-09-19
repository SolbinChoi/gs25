package kr.ac.sungkyul.gs25.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.gs25.dao.GuestbookDao;
import kr.ac.sungkyul.gs25.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	private final int LIST_PAGESIZE = 5; // 리스팅되는 게시물의 수
	private final int LIST_BLOCKSIZE = 5; // 페이지 리스트에서 표시되는 페이지 수
	
	public Map<String, Object> list(Long page){
		long totalCount= guestbookDao.getCal();  // 전체 게시물 갯수
		long pageCount = (long) Math.ceil((double) totalCount / LIST_PAGESIZE); // 페이지 갯수
	    long blockCount = (long) Math.ceil((double) pageCount / LIST_BLOCKSIZE); // 블록 갯수
	    long currentBlock = (long) Math.ceil((double) page / LIST_BLOCKSIZE); // 현재 블록
	    
	 // page값 검증
	    if (page < 1) {
	       page = 1L;
	       currentBlock = 1;
	    } else if (page > pageCount) {
	       page = pageCount;
	       currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);
	    }
	    
	    // 5. 페이지를 그리기 위한 값 계산
	    long startPage = currentBlock == 0 ? 1 : (currentBlock - 1) * LIST_BLOCKSIZE + 1;
	    long endPage = (startPage - 1) + LIST_BLOCKSIZE;
	    long prevPage = (currentBlock > 1) ? (currentBlock - 1) * LIST_BLOCKSIZE : 0;
	    long nextPage = (currentBlock < blockCount) ? currentBlock * LIST_BLOCKSIZE + 1 : 0;
	    
	    List<GuestbookVo> list = guestbookDao.getList(page, LIST_PAGESIZE);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sizeList", LIST_PAGESIZE); // 리스트 되는 갯 수
		map.put("firstPage", startPage); // 시작 페이지
		map.put("lastPage", endPage); // 끝 페이지
		map.put("prevPage", prevPage); // 이전 페이지
		map.put("nextPage", nextPage); // 다음 페이지
		map.put("currentPage", page); // 현재 페이지
		map.put("pageCount", pageCount); // 페이지 갯 수
		map.put("list", list); // 리스트 정보
		map.put("totalCount", totalCount); // 전체 게시글 정보
		
		
		return map;
		
	}
	
	public void write(GuestbookVo vo){
		guestbookDao.insert(vo);
	}
	
	public void delete(GuestbookVo vo){
		guestbookDao.delete(vo);
	}
}
