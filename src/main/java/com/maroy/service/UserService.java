/**
 * 
 */
package com.maroy.service;

import org.springframework.stereotype.Service;

import com.maroy.entity.UserEntity;

/**
 * @author MaRoy
 *
 */
@Service
public interface UserService{

	public void addUser(UserEntity userEntity);
	
	public UserEntity getCurrentUser();
}
