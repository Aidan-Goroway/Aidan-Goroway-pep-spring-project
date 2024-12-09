package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
// @RequestMapping("message")
public class SocialMediaController {

    private MessageService messageService;
    private AccountService accountService;

    public SocialMediaController(MessageService messageService, AccountService accountService){
        this.messageService = messageService;
        this.accountService = accountService;
    }

    /*
    HANDLER 3: Post new Message

    As a user, I should be able to submit a new post on the endpoint POST localhost:8080/messages. 
    The request body will contain a JSON representation of a message, which should be persisted to the database, 
    but will not contain a messageId.

    The creation of the message will be successful if and only if
        - the messageText is not blank, 
        - is not over 255 characters, 
        - and postedBy refers to a real, existing user. 
    If successful, the response body should contain a JSON of the message, including its messageId. 
    The response status should be 200, which is the default. The new message should be persisted to the database.
    If the creation of the message is not successful, the response status should be 400. (Client error)
    */
    @PostMapping("/messages")
    public @ResponseBody ResponseEntity<Message> createMessage(@RequestBody Message newMessage){
        if (newMessage.getMessageText() != "" ||
            newMessage.getMessageText().length() < 256){ //add validity check later
            messageService.postNewMessage(newMessage); //update service class
            return ResponseEntity.ok(newMessage);
        }
        else{
            return ResponseEntity.status(400).body(null); //this looks questionable
        }
    }

    /*
    HANDLER 4: Get all messages

    As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/messages.

    The response body should contain a JSON representation of a list containing all messages retrieved from the database.
    It is expected for the list to simply be empty if there are no messages.
    The response status should always be 200, which is the default.
    */
    @RequestMapping("/messages")
    public @ResponseBody ResponseEntity<List<Message>> getAllMessages(){
        List<Message> allMessages = messageService.getAllMessages();
        return ResponseEntity.status(HttpStatus.OK).body(allMessages);
    }

    /*
    HANLDER 5: Get Message by messageId

    As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/messages/{messageId}.

    The response body should contain a JSON representation of the message identified by the messageId.
    It is expected for the response body to simply be empty if there is no such message.
    The response status should always be 200, which is the default.
    */
    @GetMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Message> getMessageById(@RequestParam int messageId){
        return new ResponseEntity<>(messageService.getMessageById(messageId), HttpStatus.OK); //OK = 200
    }

    /*
    HANDLER 6: Delete Message by messageId

    As a User, I should be able to submit a DELETE request on the endpoint DELETE localhost:8080/messages/{messageId}.

    The deletion of an existing message should remove an existing message from the database. 
    If the message existed, the response body should contain the number of rows updated (1). 

    The response status should be 200, which is the default.
    If the message did not exist, the response status should be 200, but the response body should be empty.
    This is because the DELETE verb is intended to be idempotent, ie, multiple calls to the DELETE endpoint should
        respond with the same type of response.
    */
    @DeleteMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<String> deleteMessageById(@PathVariable int messageId){
        messageService.deleteMessageById(messageId);
        return ResponseEntity.ok("Message deleted.");
    }

    /*
    HANDLER 7: PATCH message's text by its messageId

    As a user, I should be able to submit a PATCH request on the endpoint PATCH localhost:8080/messages/{messageId}. 
    The request body should contain a new messageText values to replace the message identified by messageId. 
    The request body can not be guaranteed to contain any other information.

    The update of a message should be successful if and only if:
        - the message id already exists 
        - and the new messageText is not blank 
        - and is not over 255 characters. 
    If the update is successful, the response body should contain the number of rows updated (1),
    and the response status should be 200, which is the default. The message existing on the database
    should have the updated messageText.

    If the update of the message is not successful for any reason, the response status should be 400. (Client error)
    */
    @PatchMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<String> patchMessageById(@RequestParam int messageId, @RequestParam String messageText){
        messageService.patchMessageById(messageId, messageText);
        return ResponseEntity.ok("Message updated.");
    }

    /*
    HANDLER 8: All messages from user
    
    As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/accounts/{accountId}/messages.

    The response body should contain a JSON representation of a list containing all messages posted by a particular user,
    which is retrieved from the database. 
    It is expected for the list to simply be empty if there are no messages.
    The response status should always be 200, which is the default.
    */
    @GetMapping("/accounts/{accountId}/messages")
    public @ResponseBody ResponseEntity<List<Message>> getMessagesByUser(@PathVariable int user){
        List<Message> messagesFromUser = messageService.getMessagesByUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(messagesFromUser);
    }

}
