package org.project.joboffers.Service;

import org.project.joboffers.DALInterfaces.IMessageDAL;
import org.project.joboffers.DALInterfaces.IUserDAL;
import org.project.joboffers.DTO.MessageDTO;
import org.project.joboffers.EmailConfig.IEmailSenderService;
import org.project.joboffers.Model.Message;
import org.project.joboffers.Model.User;
import org.project.joboffers.ServiceInterfaces.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service @Transactional
public class MessageService implements IMessageService {
    private final IUserDAL userDAL;
    private final IMessageDAL messageDAL;
    private final IEmailSenderService senderService;

    @Autowired
    public MessageService(IUserDAL userDAL, IMessageDAL messageDAL,IEmailSenderService senderService) {
        this.userDAL = userDAL;
        this.messageDAL = messageDAL;
        this.senderService = senderService;
    }

    @Override
    public boolean sendMessage(MessageDTO messageDTO) {
        User sender = userDAL.getUserByUsername(messageDTO.getSenderUser());
        User receiver = userDAL.getUserByUsername(messageDTO.getReceiverUser());
        if(sender != null && receiver != null){
            Message message = new Message(messageDTO.getSubject(), messageDTO.getMessage(), LocalDateTime.now(), sender, receiver);
            messageDAL.addMessage(message);
            senderService.sendEmail(receiver.getEmail(), messageDTO.getSubject(), messageDTO.getMessage());
            return true;
        }
        return false;
    }

    @Override
    public List<MessageDTO> getReceivedMessages(String username) {
        User user = userDAL.getUserByUsername(username);
        if(user != null){
            List<Message> messages = messageDAL.getAllReceivedMessagesByUser(user);
            return convertEntityToDTO(messages);
        }
        return null;
    }

    @Override
    public List<MessageDTO> getSentMessages(String username) {
        User user = userDAL.getUserByUsername(username);
        if(user != null){
            List<Message> messages = messageDAL.getAllSentMessagesByUser(user);
            return convertEntityToDTO(messages);
        }
        return null;
    }

    @Override
    public MessageDTO getMessageById(String messageId) {
        Message message = messageDAL.getMessageById(messageId);
        return new MessageDTO(message.getMessageId(), message.getSubject(), message.getMessage(),
                message.getSenderUser().getUsername(), message.getReceiverUser().getUsername());
    }

    @Override
    public boolean deleteMessage(String messageId) {
        Message message = messageDAL.getMessageById(messageId);
        if(message != null){
            messageDAL.deleteMessage(message);
            return true;
        }
        return false;
    }


    private List<MessageDTO> convertEntityToDTO(List<Message> messages){
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message: messages) {
            MessageDTO messageDTO = new MessageDTO(message.getMessageId(),
                    message.getSubject(),
                    message.getMessage(),
                    message.getSenderUser().getUsername(),
                    message.getReceiverUser().getUsername());
            messageDTOS.add(messageDTO);
        }
        return messageDTOS;
    }
}
