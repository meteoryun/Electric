package com.meteor.electric.domain.start;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Xuyunfei
 * 项目搭建初始环境后测试类
 *CREATE TABLE Elec_Text(
	textID varchar(50) not null,
	textName varchar(50),
	textDate datetime,
	textRemark varchar(500)
)
 */
public class ElectricText implements Serializable{

	private static final long serialVersionUID = 1L;

	private String textID;
	private String textName;
	private Date textDate;
	private String textRemark;
	
	public String getTextID() {
		return textID;
	}
	public void setTextID(String textID) {
		this.textID = textID;
	}
	public String getTextName() {
		return textName;
	}
	public void setTextName(String textName) {
		this.textName = textName;
	}
	public Date getTextDate() {
		return textDate;
	}
	public void setTextDate(Date textDate) {
		this.textDate = textDate;
	}
	public String getTextRemark() {
		return textRemark;
	}
	public void setTextRemark(String textRemark) {
		this.textRemark = textRemark;
	}
	
	@Override
	public String toString() {
		return "ElectricText [textID=" + textID + ", textName=" + textName + ", textDate=" + textDate + ", textRemark="
				+ textRemark + "]";
	}
}
