package kr.ac.sungkyul.gs25.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.gs25.dao.TestDao;
import kr.ac.sungkyul.gs25.vo.TestVo;

@Service
public class TestService {
	@Autowired
	private TestDao testDao;
	
	public void insert(TestVo testvo) {
		testDao.insert(testvo);
	}
}
