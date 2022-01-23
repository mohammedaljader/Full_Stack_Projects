package org.project.joboffers.Model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="messages")
public class Message implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false,length = 36)
    private String messageId;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDateTime sentAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "SenderUser_id")
    private User senderUser;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ReceiverUser_id")
    private User receiverUser;


    public Message(String subject, String message, LocalDateTime sentAt, User senderUser, User receiverUser) {
        this.subject = subject;
        this.message = message;
        this.sentAt = sentAt;
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
    }

}
