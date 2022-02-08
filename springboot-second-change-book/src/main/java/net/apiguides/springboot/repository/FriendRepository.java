package net.apiguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.apiguides.springboot.entity.FriendData;

@Repository
public interface FriendRepository extends JpaRepository<FriendData,Long>{

}
