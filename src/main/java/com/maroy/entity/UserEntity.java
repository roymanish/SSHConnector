/**
 * 
 */
package com.maroy.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author MaRoy
 *
 */

//@Entity
//@Table(name="USER_DATA")
public class UserEntity implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	//@Id
	//@Column(name="USER_ID")
	//@GeneratedValue
	private String id;
	
	//@Column(name="FIRST_NAME")
	private String firstName;
	
	//@Column(name="LAST_NAME")
	private String lastName;
	
	//@Column(name="USER_NAME")
	private String username;
	
	//@Column(name="PASSWORD")
	private String password;
	
	
	public UserEntity() {
		//super();
		// TODO Auto-generated constructor stub
	}

	public UserEntity(String id, String firstName, String lastName, String username, String password) {
		//super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String[] roles = {"ROLE_USER","ROLE_ADMIN"};
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(String role : roles){
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
