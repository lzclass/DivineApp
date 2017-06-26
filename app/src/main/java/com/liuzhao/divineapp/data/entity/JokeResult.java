package com.liuzhao.divineapp.data.entity;


import com.liuzhao.divineapp.data.entity.main.Joke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class JokeResult implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Joke> data;

	public List<Joke> getData() {
		return data;
	}

	public void setData(List<Joke> data) {
		this.data = data;
	}
}
