package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;


@Service
public class MessageService {

    private List<Message> messageList = new ArrayList<>();
    private MessageService messageService;

    @Autowired
    public List<Message> getAllMessages(){
        return ;
    }

    @Autowired
    public Message getMessageById(){
        return ;
    }

}
