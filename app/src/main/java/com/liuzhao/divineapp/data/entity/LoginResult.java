package com.liuzhao.divineapp.data.entity;


import com.liuzhao.divineapp.data.entity.main.Joke;

import java.util.List;

/**
 * 
 * @description 用户登录返回实体
 * @author LiXiaoSong
 * @date 2015-10-29
 */
public class LoginResult extends BaseResult {

	private static final long serialVersionUID = 1L;
	private List<Joke> data;

	public List<Joke> getData() {
		return data;
	}

	public void setData(List<Joke> data) {
		this.data = data;
	}
}
