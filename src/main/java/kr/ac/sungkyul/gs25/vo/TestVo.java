package kr.ac.sungkyul.gs25.vo;

public class TestVo {
	private Long no;
	private String funny_date;
	private String angry_date;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getFunny_date() {
		return funny_date;
	}
	public void setFunny_date(String funny_date) {
		this.funny_date = funny_date;
	}
	public String getAngry_date() {
		return angry_date;
	}
	public void setAngry_date(String angry_date) {
		this.angry_date = angry_date;
	}
	@Override
	public String toString() {
		return "TestVo [no=" + no + ", funny_date=" + funny_date + ", angry_date=" + angry_date + "]";
	}
	
	
}
