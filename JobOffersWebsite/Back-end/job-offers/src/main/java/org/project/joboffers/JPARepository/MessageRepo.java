package org.project.joboffers.JPARepository;

import org.project.joboffers.Model.Message;
import org.project.joboffers.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, String> {
    Message findByMessageId(String id);
    List<Message> getAllByReceiverUser(User user);
    List<Message> getAllBySenderUser(User user);
}
