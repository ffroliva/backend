package br.com.ffroliva.portfolio.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
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
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name"
        })
})
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", updatable = false, nullable = false)
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
