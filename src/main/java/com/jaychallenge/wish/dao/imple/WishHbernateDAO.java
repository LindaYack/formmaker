package com.jaychallenge.wish.dao.imple;

import org.springframework.stereotype.Repository;

import com.jaychallenge.wish.bean.Wish;
import com.jaychallenge.wish.dao.WishDAO;
@Repository
public class WishHbernateDAO extends BaseDaoImpl<Wish> implements WishDAO {

}
