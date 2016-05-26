package com.meteor.electric.domain.system;

/**
 * 
 * @author	meteor-yun
 * @version	1.0
 * @createTime	2016年1月20日
 * @className	DataDictionary
 * @description	<p>系统数据字典表实体类</p>
 */
public class DataDictionary {

	private Integer id;
	private String typeKey;
	private Integer itemCode;
	private String itemName;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeKey() {
		return typeKey;
	}
	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}
	public Integer getItemCode() {
		return itemCode;
	}
	public void setItemCode(Integer itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Override
	public String toString() {
		return "DataDictionary [id=" + id + ", dictionaryType=" + typeKey + ", itemCode=" + itemCode
				+ ", itemName=" + itemName + "]";
	}
}
