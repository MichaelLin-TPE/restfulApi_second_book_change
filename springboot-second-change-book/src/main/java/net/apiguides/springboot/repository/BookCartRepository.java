package net.apiguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.apiguides.springboot.entity.BookCartData;
import net.apiguides.springboot.entity.BookData;
import net.apiguides.springboot.entity.User;

@Repository
public interface BookCartRepository extends JpaRepository<BookCartData,Long>{

	
	
	
}
