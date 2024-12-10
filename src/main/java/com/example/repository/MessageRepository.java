package com.example.repository;

import java.util.List;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository <Message, Integer>{

    public List<Message> findByPostedBy(int id);

    public Message findByMessageId(int messageId);
    
    // public Message patchById(int id, String messageText); //
}
