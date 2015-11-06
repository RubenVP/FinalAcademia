package com.academia.appForum.support;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.academia.appForum.model.MessageEntity;

public class SimpleMessageManager{

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void sendMessage(MessageEntity message) {
        // Create a thread safe "copy" of the template message and customize it
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(message.getRecipientUsersEntity().getEmail());
        msg.setText(
            "Hello " + message.getRecipientUsersEntity().getName() + " "
                + message.getRecipientUsersEntity().getLastName() + "!"
                + ", your question " + message.getQuestionEntity().getTitle() + " "
                + "on section " + message.getQuestionEntity().getCategory() + " "
                + "has been answered by " + message.getSenderUsersEntity().getUsername() + " "
                + " on " + message.getMessageDate());
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

}