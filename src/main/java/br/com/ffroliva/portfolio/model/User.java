package br.com.ffroliva.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class User extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Email
    @Column(name="username", nullable = false)
    private String username;

    @NotNull
    @Column(name="password", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.role")
    private List<UserRole> userRoles = new ArrayList<>();

    public static User of(String username, String password, List<UserRole> roles) {
        return new User(null, username, password, roles);
    }
}
