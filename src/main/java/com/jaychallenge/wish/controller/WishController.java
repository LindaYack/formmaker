package com.jaychallenge.wish.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jaychallenge.wish.bean.Wish;
import com.jaychallenge.wish.service.WishServiceI;

@Controller
@RequestMapping("/")
public class WishController extends BaseController {
	private Map<String, Object> root;
	private WishServiceI wishService;

	public Map<String, Object> getRoot() {
		return root;
	}

	public void setRoot(Map<String, Object> root) {
		this.root = root;
	}

	public WishServiceI getWishService() {
		return wishService;
	}

	@Autowired
	public void setWishService(WishServiceI wishService) {
		this.wishService = wishService;
	}

	@RequestMapping("/sendWish")
	@ResponseBody
	public Map<String, Object> sendWish(HttpServletRequest request) {
		String sendto=request.getParameter("sendto");
		String wish=request.getParameter("wish");
		String signname=request.getParameter("signname");
		root = new HashMap<String, Object>();
		if(StringUtils.isEmpty(sendto)){
			root.put("message", "许愿的对象没有填写！");
			return root;
		}
		if(StringUtils.isEmpty(wish)){
			root.put("message", "许愿的内容没有填写！");
			return root;
		}
		if(StringUtils.isEmpty(signname)){
			root.put("message", "许愿者签名没有填写！");
			return root;
		}
		// 根据传入的参数新建愿望
		Wish mywish = new Wish();
		mywish.setSendTo(sendto);
		mywish.setWish(wish);
		mywish.setSign(signname);
		mywish.setIp(request.getRemoteAddr());
		if(wishService.addWish(mywish)){
			root.put("message", "ok");
		}else{
			root.put("message", "保存许愿出错！");
		}
		return root;
	}
	@RequestMapping("/getWish")
	@ResponseBody
	public Wish getWish() {
		return wishService.getRandomWish();
	}
}
