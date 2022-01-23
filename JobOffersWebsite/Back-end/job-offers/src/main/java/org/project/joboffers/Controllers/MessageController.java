package org.project.joboffers.Controllers;

import org.project.joboffers.DTO.MessageDTO;
import org.project.joboffers.DTO.Response.MessageResponse;
import org.project.joboffers.ServiceInterfaces.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/msg")
public class MessageController {
    private final IMessageService messageService;

    @Autowired
    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody MessageDTO messageDTO){
        if(messageService.sendMessage(messageDTO)){
            return ResponseEntity.ok().body(new MessageResponse("Message sent successfully!!"));
        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("Oops, there is a problem! Please try again!"));
        }
    }

    @GetMapping("/all/sent/{id}")
    public ResponseEntity<List<MessageDTO>> getAllSentMessages(@PathVariable String id) {
        List<MessageDTO> messageDTOS = messageService.getSentMessages(id);
        if(messageDTOS != null) {
            return ResponseEntity.ok().body(messageDTOS);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all/received/{id}")
    public ResponseEntity<List<MessageDTO>> getAllReceivedMessages(@PathVariable String id) {
        List<MessageDTO> messageDTOS = messageService.getReceivedMessages(id);
        if(messageDTOS != null) {
            return ResponseEntity.ok().body(messageDTOS);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO> getMessage(@PathVariable String id) {
        MessageDTO messageDTO = messageService.getMessageById(id);
        if(messageDTO != null) {
            return ResponseEntity.ok().body(messageDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteMessage(@PathVariable String id) {
        if(messageService.deleteMessage(id)) {
            return ResponseEntity.ok().body(new MessageResponse("Message deleted successfully!!"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Oops, there is a problem.. Please try again!!"));
        }
    }
}

