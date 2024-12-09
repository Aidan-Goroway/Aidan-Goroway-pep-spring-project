package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;


@Service
public class MessageService{
    
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    // Handler 4
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
        // return mes;
    }

    // Handler 5
    public Message getMessageById(int messageId){
        return messageRepository.getById(messageId);
    }

    // Handler 6
    public Message deleteMessageById(int messageId){
        return messageRepository.deleteById(messageId);
    }

    //Handler 7
    // public Message patchMessageById(int messageId, String messageText){
    //     return messageRepository.
    // }

    //Handler 8
    public List<Message> getMessagesByUser(int userId){
        return messageRepository.findByPostedBy(userId);
    }

}
