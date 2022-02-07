package net.apiguides.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.apiguides.springboot.entity.CheckOutProductList;

@Repository
public interface CheckOutListRepository extends JpaRepository<CheckOutProductList,Long> {

}
