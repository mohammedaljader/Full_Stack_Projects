package org.project.joboffers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private String messageId;
    private String subject;
    private String message;
    private String senderUser;
    private String receiverUser;

    public MessageDTO(String subject, String message, String senderUser, String receiverUser) {
        this.subject = subject;
        this.message = message;
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
    }
}
