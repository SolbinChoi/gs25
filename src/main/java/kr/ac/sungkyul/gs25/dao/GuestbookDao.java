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
	/*
	private Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("error : " + e);
		}
		return conn;
	}
	*/
	public int getCal(){
		int totalCount = sqlSession.selectOne("guestbook.getCal");
		return totalCount;
	}
	
	public List<GuestbookVo> getList(Long page,int pagesize){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page_top", (page-1)*pagesize + 1);
		map.put("page_bottom", page * pagesize);
		
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList", map);
		return list;
	}
	/*
	public List<GuestbookVo> getList(Long page, int pagesize){
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		Connection conn = null;
	    PreparedStatement pstmt  = null;
	    ResultSet rs = null;
	    try {
	         conn = getConnection();
	         
	         String sql = "select * from(select c.*, rownum rn from(select a.no, a.content, a.reg_date, a.user_no, a.store_no, b.email from guestbook a, users b where a.user_no = b.no and a.store_no = 1 order by no DESC) c) where ?<= rn and rn <=?";
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setLong(1, (page-1)*pagesize + 1);
        	 pstmt.setLong(2, page * pagesize);
        	 rs = pstmt.executeQuery();
        	 
        	 while (rs.next()) {
        		 Long no = rs.getLong(1);
        		 String content = rs.getString(2);
        		 String reg_date = rs.getString(3);
        		 Long user_no = rs.getLong(4);
        		 Long store_no = rs.getLong(5);
        		 String email = rs.getString(6);
        		 
        		 GuestbookVo vo = new GuestbookVo();
        		 vo.setNo(no);
        		 vo.setContent(content);
        		 vo.setReg_date(reg_date);
        		 vo.setUser_no(user_no);
        		 vo.setStore_no(store_no);
        		 vo.setEmail(email);
        		 
        		 list.add(vo);
        	 }
	    }catch (SQLException e) {
	         System.out.println("error : " + e);
	    } finally {
	    	try {

	            if (rs != null) {
	               rs.close();
	            }

	            if (pstmt != null) {
	            	pstmt.close();
	            }
	            if (conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            System.out.println("error : " + e);
	         }
	      }
		return list;
	}
	*/
	public void insert(GuestbookVo vo){
		sqlSession.insert("guestbook.insert", vo);
	}
	
	public void delete(GuestbookVo vo){
		sqlSession.delete("guestbook.delete",vo);
	}
	
}
