package com.wx.carloadmember.dto.login;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.wx.dto.BaseObject;
import com.wx.enums.common.ClientType;

/**
 * 登录实体对象
 */
public class LoginDto extends BaseObject {

	private static final long serialVersionUID = 8779357140975961110L;
//	@NotNull // 不允许为空
//	@Size(min = 1, max = 20) // 长度或大小范围
//	@Min(18) // 最小值
//  @Max(100) // 最大值
//	@Past // 必须为一个过去的时间
//	@Future // 必须为一个未来的时间
//	@Pattern(regexp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")
	private String username;
	private String password;
	@NotNull
	private String ip;
	private ClientType clientType;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

}
