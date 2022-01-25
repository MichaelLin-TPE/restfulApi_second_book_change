package net.apiguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.apiguides.springboot.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	
	
	
}
