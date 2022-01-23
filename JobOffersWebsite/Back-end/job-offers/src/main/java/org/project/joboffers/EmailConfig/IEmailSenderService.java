package org.project.joboffers.EmailConfig;

public interface IEmailSenderService {
    void sendEmail(String toEmail, String subject, String body);
}
