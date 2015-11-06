package com.academia.appForum.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "MESSAGE")
public class MessageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MESSAGE_ID", columnDefinition = "INTEGER")
	private Integer id;

	@NotEmpty(message = "Description cannot be empty")
	@Column(name = "DESCRIPTION", columnDefinition = "VARCHAR", length = 2550)
	private String description;

	@ManyToOne(optional = false)
	@JoinColumn(name = "QUESTION_ID", nullable = false)
	private QuestionEntity questionEntity;

	@ManyToOne(optional = false)
	@JoinColumn(name = "SENDER_USER_ID", nullable = false)
	private UsersEntity senderUsersEntity;

	@ManyToOne(optional = false)
	@JoinColumn(name = "RECIPIENT_USER_ID", nullable = false)
	private UsersEntity recipientUsersEntity;

	@Column(name = "MESSAGE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date messageDate;

	@Column(name = "IS_NEW", columnDefinition = "BOOLEAN")
	private Boolean isNew;

	public MessageEntity() {
		super();
	}

	public MessageEntity(String description, QuestionEntity questionEntity, UsersEntity senderUsersEntity,
			UsersEntity recipientUsersEntity, Date messageDate, Boolean isNew) {
		super();
		this.description = description;
		this.questionEntity = questionEntity;
		this.senderUsersEntity = senderUsersEntity;
		this.recipientUsersEntity = recipientUsersEntity;
		this.messageDate = messageDate;
		this.isNew = isNew;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public QuestionEntity getQuestionEntity() {
		return questionEntity;
	}

	public void setQuestionEntity(QuestionEntity questionEntity) {
		this.questionEntity = questionEntity;
	}

	public UsersEntity getSenderUsersEntity() {
		return senderUsersEntity;
	}

	public void setSenderUsersEntity(UsersEntity senderUsersEntity) {
		this.senderUsersEntity = senderUsersEntity;
	}

	public UsersEntity getRecipientUsersEntity() {
		return recipientUsersEntity;
	}

	public void setRecipientUsersEntity(UsersEntity recipientUsersEntity) {
		this.recipientUsersEntity = recipientUsersEntity;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final MessageEntity other = (MessageEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MessageEntity [id=" + id + ", description=" + description + ", questionEntity=" + questionEntity
				+ ", senderUsersEntity=" + senderUsersEntity + ", recipientUsersEntity=" + recipientUsersEntity
				+ ", messageDate=" + messageDate + ", isNew=" + isNew + "]";
	}
}
