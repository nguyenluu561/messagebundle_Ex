package com.exercise.common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Getter;

@ManagedBean(name="regex")
@ViewScoped
public class RegularExpression {
	
	@Getter
	private String emailRegex = "[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,6})";
	
	
}
