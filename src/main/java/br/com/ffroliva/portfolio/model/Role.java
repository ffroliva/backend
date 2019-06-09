package br.com.ffroliva.portfolio.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name="name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.user")
    private List<UserRole> roles = new ArrayList<>();
}
