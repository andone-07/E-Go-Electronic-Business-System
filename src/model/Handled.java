package model;

public class Handled {
	private int id;
	private int orNum;
	private String orName;
	private String orAmount;
	private String orUserno;
	private String orCus;
	private String orAddress;
	private String orPhone;
	
	private int page=1;//当前查询第几页，默认第一页
	private int pageSize=5;//每页的数据条数
	private String key;//搜索词
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the orNum
	 */
	public int getOrNum() {
		return orNum;
	}
	/**
	 * @param orNum the orNum to set
	 */
	public void setOrNum(int orNum) {
		this.orNum = orNum;
	}
	/**
	 * @return the orName
	 */
	public String getOrName() {
		return orName;
	}
	/**
	 * @param orName the orName to set
	 */
	public void setOrName(String orName) {
		this.orName = orName;
	}
	/**
	 * @return the orAmount
	 */
	public String getOrAmount() {
		return orAmount;
	}
	/**
	 * @param orAmount the orAmount to set
	 */
	public void setOrAmount(String orAmount) {
		this.orAmount = orAmount;
	}
	/**
	 * @return the orUserno
	 */
	public String getOrUserno() {
		return orUserno;
	}
	/**
	 * @param orUserno the orUserno to set
	 */
	public void setOrUserno(String orUserno) {
		this.orUserno = orUserno;
	}
	/**
	 * @return the orCus
	 */
	public String getOrCus() {
		return orCus;
	}
	/**
	 * @param orCus the orCus to set
	 */
	public void setOrCus(String orCus) {
		this.orCus = orCus;
	}
	/**
	 * @return the orAddress
	 */
	public String getOrAddress() {
		return orAddress;
	}
	/**
	 * @param orAddress the orAddress to set
	 */
	public void setOrAddress(String orAddress) {
		this.orAddress = orAddress;
	}
	/**
	 * @return the orPhone
	 */
	public String getOrPhone() {
		return orPhone;
	}
	/**
	 * @param orPhone the orPhone to set
	 */
	public void setOrPhone(String orPhone) {
		this.orPhone = orPhone;
	}
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

}
