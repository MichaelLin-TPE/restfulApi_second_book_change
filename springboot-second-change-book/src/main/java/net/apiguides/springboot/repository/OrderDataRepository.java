package net.apiguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.apiguides.springboot.entity.OrderData;

@Repository
public interface OrderDataRepository extends JpaRepository<OrderData,Long>{

}
