package com.academia.appForum.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "QUESTION")
public class QuestionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUESTION_ID", columnDefinition = "INTEGER")
	private Integer id;

	@Column(name = "CATEGORY", nullable = false, columnDefinition = "VARCHAR", length = 20)
	@Enumerated(EnumType.STRING)
	private CategoryEnum category;

	@NotEmpty(message = "Title cannot be empty")
	@Column(name = "TITLE", columnDefinition = "VARCHAR", length = 255)
	private String title;

	@NotEmpty(message = "Description cannot be empty")
	@Column(name = "DESCRIPTION", columnDefinition = "VARCHAR", length = 2550)
	private String description;

	@Column(name = "ANSWERED", columnDefinition = "BOOLEAN")
	private Boolean answered;

	@OneToOne(optional = false)
	@JoinColumn(name = "USERS_ID", nullable = false)
	private UsersEntity userEntity;

	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	public QuestionEntity() {
		super();
	}

	public QuestionEntity(String title, String description, CategoryEnum category) {
		super();
		this.title = title;
		this.description = description;
		this.category = category;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public UsersEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UsersEntity userEntity) {
		this.userEntity = userEntity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CategoryEnum getCategory() {
		return category;
	}

	public void setCategory(CategoryEnum category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAnswered() {
		return answered;
	}

	public void setAnswered(Boolean answered) {
		this.answered = answered;
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
		final QuestionEntity other = (QuestionEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QuestionEntity [id=" + id + ", category=" + category + ", title=" + title + ", description="
				+ description + ", answered=" + answered + ", userEntity=" + userEntity + ", createDate=" + createDate
				+ "]";
	}
}
