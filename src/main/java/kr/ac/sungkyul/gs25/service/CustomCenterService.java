package kr.ac.sungkyul.gs25.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.sungkyul.gs25.dao.CustomCenterDao;
import kr.ac.sungkyul.gs25.vo.AttachFileVO;
import kr.ac.sungkyul.gs25.vo.CustomBoardVo;

@Service
public class CustomCenterService {

	private static final int LIST_PAGESIZE = 5; // 리스팅 되는 게시물 수
	private static final int LIST_BLOCKSIZE = 3; // 페이지 리스트에 표시되는 페이지 수

	@Autowired
	private CustomCenterDao customdao;

	public Map<String, Object> list(String spage, String keyword) {

		int page = Integer.parseInt(spage);

		int totalCount = customdao.getTotalCount();
		int pageCount = (int) Math.ceil((double) totalCount / LIST_PAGESIZE);
		int blockCount = (int) Math.ceil((double) pageCount / LIST_BLOCKSIZE);
		int currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);

		// 4. page값 검증
		if (page < 1) {
			page = 1;
			currentBlock = 1;
		} else if (page > pageCount) {
			page = pageCount;
			currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);
		}

		// 5. 페이지를 그리기 위한 값 계산
		int startPage = (currentBlock - 1) * LIST_BLOCKSIZE + 1;
		int endPage = (startPage - 1) + LIST_BLOCKSIZE;
		int prevPage = (page >= startPage) ? (page - 1) : (currentBlock - 1) * LIST_BLOCKSIZE;
		int nextPage = (page <= endPage) ? (page + 1) : currentBlock * LIST_BLOCKSIZE + 1;
		int nexttoPage = (currentBlock < blockCount) ? currentBlock * LIST_BLOCKSIZE + 1 : page;
		int prevtoPage = (currentBlock > 1) ? startPage - 3 : page;

		List<CustomBoardVo> list = customdao.getList(page, LIST_PAGESIZE, keyword);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sizeList", LIST_PAGESIZE);
		map.put("firstPage", startPage);
		map.put("lastPage", endPage);
		map.put("prevPage", prevPage);
		map.put("nextPage", nextPage);
		map.put("currentPage", page);
		map.put("pageCount", pageCount);
		map.put("list", list);
		map.put("totalCount", totalCount);
		map.put("keyword", keyword);
		map.put("nexttoPage", nexttoPage);
		map.put("prevtoPage", prevtoPage);

		return map;
	}

	public void write(CustomBoardVo vo, MultipartFile file) throws Exception {
		Long no = customdao.insert(vo); // 게시판 내용 삽입 후 파일을 저장하기 위해 번호를 외래키로 받아와
		CustomBoardVo vo1 = customdao.get(no); // 외래키를 가지고 게시판의 정보를 가져와
		int groupno=vo1.getGroupNo(); // 그룹번호 얻어오기
		int orderno=vo1.getGroupOrderNo(); // 그룹 내 번호 얻어오기

		//3.orgName
		String orgName ="이미지";
		
		//file.getOriginalFilename();
		
		//4.fileSize 파일 크기
		long fileSize =file.getSize();
		
		//5.saveName 저장될 이름
		String saveName = UUID.randomUUID().toString()+ "_" + orgName;
		
		//6.path 경로
		String path ="c:\\upload";
		
		
		AttachFileVO attachFileVO = new AttachFileVO();
		attachFileVO.setNo(no);
		attachFileVO.setPath(path);
		attachFileVO.setOrgName(orgName);
		attachFileVO.setSaveName(saveName);
		attachFileVO.setFileSize(fileSize);
		attachFileVO.setGroupno(groupno);
		attachFileVO.setOrderno(orderno);
										
		customdao.insertAttachFile(attachFileVO);
		
		
		File target = new File(path, saveName);
		FileCopyUtils.copy(file.getBytes(),target);
	}

	public void delete(Integer no, int orderno){ // group과 order 번호
		customdao.delete(no, orderno); // n 이상의 그룹 내 번호를 지워주기 위함
	}
	public void delete(CustomBoardVo vo) {
		customdao.delete(vo);
	}

	public CustomBoardVo boardinfo(Long no) {
		CustomBoardVo vo = customdao.get(no);
		return vo;
	}

	public void viewcountup(Long no) { // 조회수

		customdao.updateViewCount(no);
	}
	
	public void modify(CustomBoardVo vo){
		customdao.update(vo);

	}
	
	public void updatereplyCount(int groupno, int ordernumber){ // 답글 갯 수 증가
		customdao.updatereplyCount(groupno, ordernumber);
	}
	
	public void reply(CustomBoardVo vo, MultipartFile file) throws Exception{
		Long no=customdao.reply(vo); // 외래키
		CustomBoardVo vo1 = customdao.get(no);
		int groupno=vo1.getGroupNo(); //그룹 번호 얻기
		int orderno=vo1.getGroupOrderNo(); // 그룹 내 번호 얻기
		
		  //3.orgName
		String orgName ="이미지";
		
	  //file.getOriginalFilename();
		
		//4.fileSize
		long fileSize =file.getSize();
		
		//5.saveName
		String saveName = UUID.randomUUID().toString()+ "_" + orgName;
		
		//6.path 
		String path ="c:\\upload";
		
		
		AttachFileVO attachFileVO = new AttachFileVO();
		attachFileVO.setNo(no);
		attachFileVO.setPath(path);
		attachFileVO.setOrgName(orgName);
		attachFileVO.setSaveName(saveName);
		attachFileVO.setFileSize(fileSize);
		attachFileVO.setGroupno(groupno);
		attachFileVO.setOrderno(orderno);
				
		customdao.insertAttachFile(attachFileVO);
		
		
		File target = new File(path, saveName);
		FileCopyUtils.copy(file.getBytes(),target);

	}
	
	public AttachFileVO selectAttachFileByNO(Long no){ // 외래키로 첨부파일의 모든 정보를 가져옴(view)
		return customdao.selectAttachFileByNO(no);
	}
	
	public AttachFileVO selectAttachFileByFNO(Long fNO){ // 기본키로 첨부파일의 모든 정보를 가져옴(download)
		return customdao.selectAttachFileByFNO(fNO);
	}
	
	public int getgroupno(AttachFileVO vo){
		int groupno=customdao.getgroupno(vo);
		// 첨부 파일의 그룹 번호 반환
		return groupno; 
	}
	
	public CustomBoardVo userno(int groupNo) { // 그룹 번호를 가지고 글 깊이가 1인 사용자 번호를 얻기 위해
		CustomBoardVo vo  = customdao.getList(groupNo);
		return vo;
	}
	
	
	
	

}
