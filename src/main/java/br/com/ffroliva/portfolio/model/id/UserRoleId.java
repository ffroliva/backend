package br.com.ffroliva.portfolio.model.id;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import br.com.ffroliva.portfolio.model.Role;
import br.com.ffroliva.portfolio.model.User;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserRoleId implements Serializable {

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @JsonBackReference
    @ManyToOne (fetch = FetchType.LAZY)
    private Role role;

    private UserRoleId(String username){
        this.user = User.of(username);
    }

    public static UserRoleId of(String username) {
        return new UserRoleId(username);
    }
}
