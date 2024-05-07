package com.webSocketExample.webSocketExample.repository;

import com.webSocketExample.webSocketExample.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
}
