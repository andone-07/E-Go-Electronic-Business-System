package model;

public class Goods {
	private int id;
	private String gsname;
	private String gsprice;
	private String gsamount;
	private String category;
	private String category_id;
	
	private int page=1;//��ǰ��ѯ�ڼ�ҳ��Ĭ�ϵ�һҳ
	private int pageSize=5;//ÿҳ��������
	private String key;//������
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGsname() {
		return gsname;
	}
	public void setGsname(String gsname) {
		this.gsname = gsname;
	}
	public String getGsprice() {
		return gsprice;
	}
	public void setGsprice(String gsprice) {
		this.gsprice = gsprice;
	}
	public String getGsamount() {
		return gsamount;
	}
	public void setGsamount(String gsamount) {
		this.gsamount = gsamount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
