/**
 * 
 */
package de.zettsystems.feutrainer.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author zoellerm
 *
 */
@Entity
public class User {
	@Id
	@GeneratedValue
	private Long userKey;

	@Column(nullable = false, unique = true)
	@NotNull
	@Size(min = 1, max = 255)
	private String username;

	@NotNull
	@Size(min = 60, max = 60)
	private String password;

	@NotNull
	private boolean enabled;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;

	private transient boolean passwordChanged;

	public void setPasswordChanged(boolean b) {
		this.passwordChanged = b;
	}

	public boolean isPasswordChanged() {
		return passwordChanged;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getUserKey() {
		return userKey;
	}

	protected void setUserKey(Long key) {
		this.userKey = key;
	}

	@Override
	public String toString() {
		return "User [userKey=" + userKey + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userKey == null) ? 0 : userKey.hashCode());
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
		User other = (User) obj;
		if (userKey == null) {
			if (other.userKey != null)
				return false;
		} else if (!userKey.equals(other.userKey))
			return false;
		return true;
	}

}
