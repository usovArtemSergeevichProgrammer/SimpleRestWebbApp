package model;

public class Product {
	private int id;
	private String name;
	private String detail;
	private String imgPath;
	
	public Product(int id, String name, String detail, String imgPath) {
		super();
		this.id = id;
		this.name = name;
		this.detail = detail;
		this.imgPath = imgPath;
	}
	public Product() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", detail=" + detail + ", imgPath=" + imgPath + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}
