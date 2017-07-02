/**
 * 
 */
package com.maroy.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.maroy.entity.UserEntity;

/**
 * @author MaRoy
 *
 */
@Repository
public class UserDaoImpl implements UserDao{

	//@Autowired
    //private SessionFactory sessionFactory;
	
	@Autowired
	private MongoOperations mongoTemplate;
	
	private static final String USER_COLLECTION = "USER_DATA";
	
	@Override
	public void addUser(UserEntity user){
		//this.sessionFactory.getCurrentSession().save(user);
		//this.mongoTemplate.insert(user, USER_COLLECTION);
		this.mongoTemplate.save(user, USER_COLLECTION);
	}
	
	@Override
	public UserEntity loadUserByUserName(String userName){
		//UserEntity user = (UserEntity)this.sessionFactory.getCurrentSession().createQuery("from UserEntity where username='"+userName+"'").list().get(0);
		UserEntity user = this.mongoTemplate.findOne(new Query(Criteria.where("username").is(userName)), UserEntity.class, USER_COLLECTION);
		return user;
	}
	
	/*public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}*/
}
