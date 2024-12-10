package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // Handler 3
    public Message postNewMessage(Message message){
        return null;
    }

    // Handler 4 [DONE]
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    // Handler 5
    public Message getMessageById(int messageId){
        Optional<Message> message = messageRepository.findById(messageId); //TODO: Fix later
        if (message.isPresent()){
            return message.get();
        }
        else{
            return null;
        }
    }

    // Handler 6
    public Message deleteMessageById(int messageId){
        messageRepository.deleteById(messageId);
        return messageRepository.getById(messageId);
    }

    // Handler 7
    public Message patchMessageById(int messageId, String messageText){ //TODO: fix later?
        Message message = messageRepository.findById(messageId).orElseThrow(); //
        message.setMessageText(messageText);
        messageRepository.save(message);
        return message;
    }

    // Handler 8
    public List<Message> getMessagesByUser(int userId){
        return messageRepository.findByPostedBy(userId);
    }

}
