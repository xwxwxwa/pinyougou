package com.pinyougou.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
/**
 * 这个类主要是后端返回用户名的方法
 * @author Administrator
 *
 */
public class LoginController {

	@RequestMapping("/name")
	public Map name() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Map map=new HashMap<>();
		map.put("loginName",name);
		return map;
		}
	}
