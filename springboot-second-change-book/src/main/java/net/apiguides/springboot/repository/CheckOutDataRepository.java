package net.apiguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.apiguides.springboot.entity.CheckOutData;

@Repository
public interface CheckOutDataRepository extends JpaRepository<CheckOutData,Long>{

}
