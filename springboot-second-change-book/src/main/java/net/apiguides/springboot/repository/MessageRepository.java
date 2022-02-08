package net.apiguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.apiguides.springboot.entity.MessageData;


@Repository
public interface MessageRepository extends JpaRepository<MessageData, Long>{

}
