package com.wx.msg;


public interface MessageListener<T> {

	void onMessage(T message) throws Throwable;
}
