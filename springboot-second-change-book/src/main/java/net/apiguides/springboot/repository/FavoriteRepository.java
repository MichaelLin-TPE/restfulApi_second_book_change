package net.apiguides.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import net.apiguides.springboot.entity.FavoriteData;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteData,Long>{

	
}
