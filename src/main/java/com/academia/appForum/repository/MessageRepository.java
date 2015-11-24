package com.academia.appForum.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.academia.appForum.model.MessageEntity;
import com.academia.appForum.model.UsersEntity;

@Repository
@Transactional
public class MessageRepository {

	@PersistenceContext(name = "persistenceUnitH2")
	EntityManager entityManager;

	public void createMessage(MessageEntity messageEntity) {
		System.out.println("[REPO] before persist message: " + messageEntity);

		entityManager.persist(messageEntity);

		System.out.println("[REPO] message created" + messageEntity);
	}

	public List<MessageEntity> getMessages(UsersEntity recipientEntity) {
		System.out.println("[REPO] messages for user: " + recipientEntity.getName());

		return entityManager.createQuery(
				"SELECT M FROM MessageEntity M WHERE M.recipientUsersEntity = :recipientEntity ORDER BY M.messageDate",
				MessageEntity.class).setParameter("recipientEntity", recipientEntity).getResultList();
	}

	public List<MessageEntity> getNumberOfNewMessagesByUser(UsersEntity recipientEntity) {
		System.out.println("[REPO] Getting Number Of New Messages By User");

		return entityManager.createQuery(
				"SELECT M FROM MessageEntity M WHERE M.recipientUsersEntity = :recipientEntity AND M.isNew = true",
				MessageEntity.class).setParameter("recipientEntity", recipientEntity).getResultList();
	}

	public void changeMessageToOld(int messageId) {

		System.out.println("[REPO] Old message: " + messageId);

		entityManager.createQuery("UPDATE MessageEntity M SET M.isNew = false WHERE M.id = :messageId")
				.setParameter("messageId", messageId).executeUpdate();

		System.out.println("[REPO] updated message to old with id: " + messageId);
	}

	public MessageEntity getMesageById(int messageId) {
		System.out.println("[REPO] get message by id: " + messageId);

		return entityManager.createQuery("SELECT M FROM MessageEntity M WHERE M.id = :messageId", MessageEntity.class)
				.setParameter("messageId", messageId).getSingleResult();
	}

	public void deleteMessage(MessageEntity messageEntity) {
		System.out.println("[REPO] delete message: " + messageEntity);

		final MessageEntity messageToBeRemoved = entityManager.merge(messageEntity);
		entityManager.remove(messageToBeRemoved);

		System.out.println("[REPO] message with id: " + messageEntity + " deleted");

	}
}
