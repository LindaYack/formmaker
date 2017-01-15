package com.jaychallenge.wish.service;

import com.jaychallenge.wish.bean.Wish;

public interface WishServiceI {

	boolean addWish(Wish mywish);

	Wish getRandomWish();

}
