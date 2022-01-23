package org.project.joboffers.DAL;

import org.project.joboffers.DALInterfaces.IMessageDAL;
import org.project.joboffers.JPARepository.MessageRepo;
import org.project.joboffers.Model.Message;
import org.project.joboffers.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDAL implements IMessageDAL {
    private final MessageRepo messageRepo;

    @Autowired
    public MessageDAL(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public List<Message> getAllReceivedMessagesByUser(User user) {
        return messageRepo.getAllByReceiverUser(user);
    }

    @Override
    public List<Message> getAllSentMessagesByUser(User user) {
        return messageRepo.getAllBySenderUser(user);
    }

    @Override
    public void addMessage(Message message) {
      messageRepo.save(message);
    }

    @Override
    public void deleteMessage(Message message) {
       messageRepo.delete(message);
    }

    @Override
    public Message getMessageById(String id) {
        return messageRepo.findByMessageId(id);
    }
}
