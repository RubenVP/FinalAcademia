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

@Entity
@Table(name="comment")
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COMMENT_ID", columnDefinition = "INTEGER")
	private Integer id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="QUESTION_ID", nullable = false)
	private QuestionEntity questionEntity;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="USERS_ID", nullable = false)
	private UsersEntity userEntity;
	
	@Column(name="DESCRIPTION", columnDefinition = "VARCHAR", length = 255)
	private String description;
	
	@Column(name="ANSWER_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date answerDate;
	
	@Column(name = "HELPFUL", columnDefinition = "BOOLEAN")
	private Boolean helpful;

	public Boolean getHelpful() {
		return helpful;
	}

	public void setHelpful(Boolean helpful) {
		this.helpful = helpful;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public QuestionEntity getQuestionEntity() {
		return questionEntity;
	}

	public void setQuestionEntity(QuestionEntity questionEntity) {
		this.questionEntity = questionEntity;
	}

	public UsersEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UsersEntity userEntity) {
		this.userEntity = userEntity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		CommentEntity other = (CommentEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommentEntity [id=" + id + ", questionEntity=" + questionEntity + ", userEntity=" + userEntity
				+ ", description=" + description + ", answerDate=" + answerDate + "]";
	}
}
