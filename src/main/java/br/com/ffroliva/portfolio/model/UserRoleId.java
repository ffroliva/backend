package br.com.ffroliva.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@Embeddable
public class UserRoleId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne (fetch = FetchType.LAZY)
    private Role role;
}
