package br.com.ffroliva.portfolio.model;


import br.com.ffroliva.portfolio.model.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.user")
    private List<UserRole> userRoles = new ArrayList<>();
}
