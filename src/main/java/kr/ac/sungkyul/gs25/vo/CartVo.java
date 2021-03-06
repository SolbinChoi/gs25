package kr.ac.sungkyul.gs25.vo;

public class CartVo {
	private Long user_no; // 회원번호
	private Long product_no; // 상품번호
	private String name; // 상품명
	private int price; // 상품 가격
	private String maker; // 제조사
	private Long kind_no; // 상품 분류번호
	private String imageurl; // 상품이미지
	public Long getUser_no() {
		return user_no;
	}
	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}
	public Long getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Long product_no) {
		this.product_no = product_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public Long getKind_no() {
		return kind_no;
	}
	public void setKind_no(Long kind_no) {
		this.kind_no = kind_no;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	@Override
	public String toString() {
		return "CartVo [user_no=" + user_no + ", product_no=" + product_no + ", name=" + name + ", price=" + price
				+ ", maker=" + maker + ", kind_no=" + kind_no + ", imageurl=" + imageurl + "]";
	}
	
	
}
