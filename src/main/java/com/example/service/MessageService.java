package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;


@Service
public class MessageService{
    
    private MessageRepository messageRepository;
    private AccountRepository accountRepository; //needed for postNewMessage

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    // Handler 3
    public Message postNewMessage(Message message){

        if (accountRepository.existsById(message.getPostedBy())){
            return messageRepository.save(message);
        }
        else{
            return null;
        }

    }

    // Handler 4
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    // Handler 5
    public Message getMessageById(int messageId){

        Optional<Message> message = messageRepository.findById(messageId);

        if (message.isPresent()){
            return message.get();
        }
        else{
            return null;
        }
    }

    // Handler 6
    public Message deleteMessageById(int messageId){

        Optional<Message> message = messageRepository.findById(messageId);
        
        if (message.isPresent()){
            messageRepository.deleteById(messageId);
            return message.get();
        }
        else {
            return null;
        }
    }

    // Handler 7
    public Message patchMessageById(int messageId, String messageText){
        
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        
        if (optionalMessage.isPresent()){
            if (messageText != null &&
                !messageText.isBlank() &&
                messageText.length() < 256){
                    Message message = optionalMessage.get();
                    message.setMessageText(messageText);
                    return messageRepository.save(message);
            }
        }
        return null;
    }

    // Handler 8
    public List<Message> getMessagesByUser(int postedBy){
        return messageRepository.findByPostedBy(postedBy);
    }

}
