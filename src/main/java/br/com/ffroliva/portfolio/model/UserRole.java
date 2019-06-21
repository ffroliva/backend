package br.com.ffroliva.portfolio.model;

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
}