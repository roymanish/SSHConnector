/**
 * 
 */
package com.maroy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maroy.dao.UserDao;
import com.maroy.entity.UserEntity;

/**
 * @author MaRoy
 *
 */
@Service
public class UserServiceImpl implements UserDetailsService,UserService{

	@Autowired
	private UserDao userDao;
	
	private UserEntity user;
	
	//@Transactional
	public void addUser(UserEntity userEntity){
		userDao.addUser(userEntity);
	}
	
	@Override
	//@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		user = userDao.loadUserByUserName(username);
		return user;
	}
	
	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserEntity getCurrentUser() {
		// TODO Auto-generated method stub
		return user;
	}
}
