package br.com.ffroliva.portfolio.model.id;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import br.com.ffroliva.portfolio.model.Role;
import br.com.ffroliva.portfolio.model.User;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class UserRoleId implements Serializable {

	private static final long serialVersionUID = 7655602272158439673L;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	@JoinColumn(name="user_id")
	private User user;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Role.class)
	@JoinColumn(name="role_id")
	private Role role;

	private UserRoleId(String username) {
		this.user = User.of(username);
	}

	public static UserRoleId of(String username) {
		return new UserRoleId(username);
	}

}
