package br.com.ffroliva.portfolio.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.NaturalId;

import br.com.ffroliva.portfolio.model.enums.RoleName;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @NotNull
    @Column(name="name", nullable = false)
    private RoleName name;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.role")
    private List<UserRole> userRoles = new ArrayList<>();

    private Role(RoleName roleName){
        this.name = roleName;
    }

    public static Role of(RoleName roleName){
        return new Role(roleName);
    }
}
