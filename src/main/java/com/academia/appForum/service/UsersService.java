package com.academia.appForum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.academia.appForum.model.UserRoleEntity;
import com.academia.appForum.model.UsersEntity;
import com.academia.appForum.repository.UsersRepository;


@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public void addNewUser(UsersEntity user){
		user.setEnabled(true);
		usersRepository.addUser(user);
		
		System.out.println(" ");
		System.out.println( "---------- user ----------" );
		System.out.println(user);
	}
	
	public void addNewUserRole(UsersEntity user){
		
		String userName = user.getUsername();
		
		UserRoleEntity userRoleEntity = new UserRoleEntity();
		userRoleEntity.setUsername(userName);
		userRoleEntity.setRole("ROLE_USER");
		
		usersRepository.addUserRole(userRoleEntity);
		
		System.out.println(" ");
		System.out.println( "---------- userRoleEntity ----------" );
		System.out.println(userRoleEntity);
	}

	public UsersEntity getUserByUsername(String username){
		return usersRepository.findUserByName(username);
	}
	
	public UsersEntity getUserInSession(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	    
	    System.out.println("session username:" + username);
	    
	    return usersRepository.findUserByName(username);
	}
}
