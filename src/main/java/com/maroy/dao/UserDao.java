/**
 * 
 */
package com.maroy.dao;

import com.maroy.entity.UserEntity;

/**
 * @author MaRoy
 *
 */
public interface UserDao {

	public void addUser(UserEntity user);
	
	public UserEntity loadUserByUserName(String userName);
}
