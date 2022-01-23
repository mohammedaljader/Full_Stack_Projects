package org.project.joboffers.DALInterfaces;

import org.project.joboffers.Model.Message;
import org.project.joboffers.Model.User;

import java.util.List;

// TODO: 11/16/2021 starting with this functionality
public interface IMessageDAL {
    List<Message> getAllReceivedMessagesByUser(User user);
    List<Message> getAllSentMessagesByUser(User user);
    void addMessage(Message message);
    void deleteMessage(Message message);
    Message getMessageById(String id);
}
