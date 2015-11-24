package com.meteor.electric.domain.system;

import java.io.Serializable;
import java.util.Date;

public class RunMonitorCommonInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String stationRunMsg;
	private String deviceRunMsg;
	private Date createDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStationRunMsg() {
		return stationRunMsg;
	}
	public void setStationRunMsg(String stationRunMsg) {
		this.stationRunMsg = stationRunMsg;
	}
	public String getDeviceRunMsg() {
		return deviceRunMsg;
	}
	public void setDeviceRunMsg(String deviceRunMsg) {
		this.deviceRunMsg = deviceRunMsg;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "RunMonitorCommonInfo [id=" + id + ", stationRunMsg=" + stationRunMsg + ", deviceRunMsg=" + deviceRunMsg
				+ ", createDate=" + createDate + "]";
	}
	
}
