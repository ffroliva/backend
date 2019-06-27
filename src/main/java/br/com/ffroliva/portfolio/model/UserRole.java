package br.com.ffroliva.portfolio.model;

import br.com.ffroliva.portfolio.model.id.UserRoleId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserRole extends BaseEntity<UserRoleId> {

    @EmbeddedId
    private UserRoleId id;

    public UserRoleId getId(){
        return id;
    }

    public static UserRole of(User user, Role role){
        return new UserRole(new UserRoleId(user,role));
    }

}
