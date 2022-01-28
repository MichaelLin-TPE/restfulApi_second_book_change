package net.apiguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.apiguides.springboot.entity.UserBasicData;

@Repository

public interface UserBasicDataRepository extends JpaRepository<UserBasicData, Long>{

}
