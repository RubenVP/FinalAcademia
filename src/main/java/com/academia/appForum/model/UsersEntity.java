package com.academia.appForum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name = "USERS")
public class UsersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USERS_ID", columnDefinition = "INTEGER")
	private Integer id;

	@Column(name = "USERNAME", columnDefinition = "VARCHAR", length = 50)
	@NotEmpty(message = "Username cannot be empty")
	@Pattern(regexp = "[a-z]*[A-Z]*[0-9]*", message = "Only letters and numbers")
	private String username;

	@Column(name = "PASSWORD", columnDefinition = "VARCHAR", length = 128)
	@NotEmpty(message = "Username cannot be empty")
	private String password;

	@Column(name = "ENABLED", columnDefinition = "BOOLEAN")
	private Boolean enabled;

	@Column(name = "NAME", columnDefinition = "VARCHAR", length = 50)
	@NotEmpty(message = "Name cannot be empty")
	private String name;

	@Column(name = "LAST_NAME", columnDefinition = "VARCHAR", length = 50)
	private String lastName;

	@Column(name = "EMAIL", columnDefinition = "VARCHAR", length = 255)
	@NotEmpty(message = "E-mail cannot be empty")
	private String email;

	@Column(name = "image_url", columnDefinition = "VARCHAR", length = 255)
	private String imageUrl;

	@Transient
	private CommonsMultipartFile fileData;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public UsersEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

	public UsersEntity(String username, String password, Boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		final UsersEntity other = (UsersEntity) obj;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsersEntity [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", name=" + name + ", lastName=" + lastName + ", email=" + email + "]";
	}
}
