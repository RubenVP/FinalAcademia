package com.academia.appForum.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.appForum.model.MessageEntity;
import com.academia.appForum.model.QuestionEntity;
import com.academia.appForum.model.UsersEntity;
import com.academia.appForum.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	UsersService usersService;

	public void sendMessegeQuestionAndswered(QuestionEntity questionEntity) {
		final UsersEntity senderEntity = usersService.getUserInSession();
		final UsersEntity recipientEntity = questionEntity.getUserEntity();

		final String message = "Hello " + recipientEntity.getName() + " your question '" + questionEntity.getTitle()
				+ "' has been answered by " + senderEntity.getName();
		final MessageEntity messageEntity = new MessageEntity(message, questionEntity, senderEntity, recipientEntity,
				new Date(), true);

		messageRepository.createMessage(messageEntity);

		// SimpleMessageManager simpleMessageManager = new
		// SimpleMessageManager();
		// simpleMessageManager.sendMessage(messageEntity);

		System.out.println("[SERVICE]send message after answering question");
	}

	public List<MessageEntity> getMessagesForUser() {
		final UsersEntity recipientEntity = usersService.getUserInSession();

		System.out.println("[SERVICE] get aMessages For User in session " + recipientEntity);

		return messageRepository.getMessages(recipientEntity);
	}

	public Integer getNumberOfNewMessagesByUser() {

		final UsersEntity recipientEntity = usersService.getUserInSession();

		final List<MessageEntity> messages = messageRepository.getNumberOfNewMessagesByUser(recipientEntity);

		final int noMessages = messages.size();

		return noMessages;
	}

	public void changeMessageToOld(int messageId) {

		System.out.println("[SERVICE] Old message: " + messageId);

		messageRepository.changeMessageToOld(messageId);

		System.out.println("[SERVICE] updated message to old with id: " + messageId);
	}

	public void deleteMessage(int messageId) {
		System.out.println("[SERVICE] delete message: " + messageId);

		final MessageEntity messageEntity = messageRepository.getMesageById(messageId);

		messageRepository.deleteMessage(messageEntity);

		System.out.println("[SERVICE] message with id: " + messageId + " deleted");
	}
}
