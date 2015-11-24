package com.academia.appForum.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.academia.appForum.model.UserRoleEntity;
import com.academia.appForum.model.UsersEntity;

@Repository
@Transactional
public class UsersRepository {
	
	@PersistenceContext(name="persistenceUnitH2")
	EntityManager entityManager;
	
	public void addUser(UsersEntity userEntity) {
		System.out.println("[REPO] before persist user: " + userEntity);
		
		entityManager.persist(userEntity);
		
		System.out.println("[REPO] new user added" + userEntity);
	}
	
	public void addUserRole(UserRoleEntity userRoleEntity) {
		System.out.println("[REPO] before persist user-role of user: " + userRoleEntity.getUsername() + " " +  userRoleEntity.getRole());
		
		entityManager.persist(userRoleEntity);
		
		System.out.println("[REPO] new user added" + userRoleEntity.getUsername() + " " +  userRoleEntity.getRole());
	}

	public UsersEntity findUserByName(String username){
		
		System.out.println("[REPO] find user by name: " + username);
		
		return entityManager.createQuery("SELECT U FROM UsersEntity U WHERE U.username = :username", UsersEntity.class)
				.setParameter("username", username).getSingleResult();
	}
}
