package de.zettsystems.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import de.zettsystems.feutrainer.domain.user.User;

public class SecurityUser extends User implements UserDetails {

	public SecurityUser(User user) {
		if (user != null) {
			this.setUserKey(user.getUserKey());
			this.setUsername(user.getUsername());
			this.setPassword(user.getPassword());
			this.setRole(user.getRole());
			this.setEnabled(user.isEnabled());
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + getRole().toString());
		authorities.add(authority);
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}