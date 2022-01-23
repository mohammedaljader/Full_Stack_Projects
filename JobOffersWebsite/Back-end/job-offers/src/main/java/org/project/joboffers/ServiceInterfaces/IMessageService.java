package org.project.joboffers.ServiceInterfaces;

import org.project.joboffers.DTO.MessageDTO;

import java.util.List;

public interface IMessageService {
    boolean sendMessage(MessageDTO messageDTO);
    List<MessageDTO> getReceivedMessages(String username);
    List<MessageDTO> getSentMessages(String username);
    MessageDTO getMessageById(String messageId);
    boolean deleteMessage(String messageId);
}
