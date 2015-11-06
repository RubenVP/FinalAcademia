package com.academia.appForum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="USER_ROLES")
public class UserRoleEntity {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ROLES_ID", columnDefinition = "INTEGER")
	private Integer id;
	
	@Column(name="USERNAME", columnDefinition = "VARCHAR", length = 45)
	@NotEmpty(message="Username cannot be empty")
	@Pattern(regexp="[a-z]*[A-Z]*[0-9]*", message="Only letters and numbers")
	private String username;
	
	@Column(name="role", columnDefinition = "VARCHAR", length = 45)
	private String role;
	
	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		UserRoleEntity other = (UserRoleEntity) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
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
		return "UserRoleEntity [username=" + username + ", role=" + role + "]";
	}
	
}
