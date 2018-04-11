package com.wx.carloadaccount.dto;

import java.io.Serializable;
import java.util.Date;

public class UserFeedbackDTO  implements Serializable {
	private Long	id;//物理主键ID
	private String	feedback_num;//反馈编号
	private String	user_id;//用户ID t_user_userlist-user_id
	private String	type_code;//	反馈类型代号 t_base_type_code_lib 表 type_kind_num种类代号为用户反馈意见类型的 type_code	
	private String	type_name;//	反馈类型名称 t_base_type_code_lib 的 type_name
	private	String title;//反馈意见标题
	private	String content;//反馈内容
	private	String mobile_phone;//手机号码
	private	String qq_num;//QQ号码
	private	String email_num;//邮箱
	private	Date publish_time;//发布时间
	
	
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMobile_phone() {
		return mobile_phone;
	}
	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}
	public String getQq_num() {
		return qq_num;
	}
	public void setQq_num(String qq_num) {
		this.qq_num = qq_num;
	}
	public String getEmail_num() {
		return email_num;
	}
	public void setEmail_num(String email_num) {
		this.email_num = email_num;
	}
	public Date getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}
}
