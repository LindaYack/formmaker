package com.jaychallenge.wish.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaychallenge.wish.bean.Wish;
import com.jaychallenge.wish.dao.WishDAO;
import com.jaychallenge.wish.service.WishServiceI;

@Service
public class WishServiceImpl implements WishServiceI {
	private WishDAO wishDAO;

	public WishDAO getWishDAO() {
		return wishDAO;
	}

	@Autowired
	public void setWishDAO(WishDAO wishDAO) {
		this.wishDAO = wishDAO;
	}

	@Override
	public boolean addWish(Wish mywish) {
		try {
			wishDAO.saveOrUpdate(mywish);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Wish getRandomWish() {
		Long count = wishDAO.count("select count(*) from Wish");
		long choose = (long) (count*Math.random()+1);
		return wishDAO.get(Wish.class, choose);
	}

}
