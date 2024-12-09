package com.example.repository;

import java.util.List;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository <Message, Integer>{

    /**
     * Get all messages, return in list.
     * @return List object of Message type. Returns emptylist if there are no messages.
     */
    public List<Message> findByPostedBy(int id);
    
    public Message patchById();
}
