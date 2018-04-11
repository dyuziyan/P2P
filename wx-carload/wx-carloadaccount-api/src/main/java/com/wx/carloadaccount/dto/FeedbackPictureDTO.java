package com.wx.carloadaccount.dto;

import java.io.Serializable;
import java.util.Date;

public class FeedbackPictureDTO  implements Serializable {
	
	private Long id;
	private String feedback_num;//反馈编号 t_sys_feedback_info_list-feedback_num
	private String pic_url_min;//小图地址
	private String pic_url_med;//中图地址
	private String pic_url_max;//大图地址
	private String pic_url_src;//原图地址
	private Long serial_number;//序号（排序用）
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFeedback_num() {
		return feedback_num;
	}
	public void setFeedback_num(String feedback_num) {
		this.feedback_num = feedback_num;
	}
	public String getPic_url_min() {
		return pic_url_min;
	}
	public void setPic_url_min(String pic_url_min) {
		this.pic_url_min = pic_url_min;
	}
	public String getPic_url_med() {
		return pic_url_med;
	}
	public void setPic_url_med(String pic_url_med) {
		this.pic_url_med = pic_url_med;
	}
	public String getPic_url_max() {
		return pic_url_max;
	}
	public void setPic_url_max(String pic_url_max) {
		this.pic_url_max = pic_url_max;
	}
	public String getPic_url_src() {
		return pic_url_src;
	}
	public void setPic_url_src(String pic_url_src) {
		this.pic_url_src = pic_url_src;
	}
	public Long getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(Long serial_number) {
		this.serial_number = serial_number;
	}
	
}
