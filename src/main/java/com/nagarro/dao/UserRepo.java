package com.nagarro.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, String> {

	User findByUsername(String username);
	
	@Query(value = "SELECT COUNT(*) FROM user", nativeQuery = true)
	public int findCountOUsers();
}
