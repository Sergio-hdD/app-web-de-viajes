package com.system.Sistemadeviajes.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.system.Sistemadeviajes.repositories.IUserRoleRepository;

@Service("userRoleService")
public class UserRoleService {
	
	@Autowired
	@Qualifier("userRoleRepository")
	public IUserRoleRepository userRoleRepository;
	
	public void saveUser(com.system.Sistemadeviajes.entities.UserRole user) {
		userRoleRepository.save(user);
	}

}