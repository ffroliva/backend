package br.com.ffroliva.portfolio.model;

import br.com.ffroliva.portfolio.model.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class User extends DateAudit<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name="username", nullable = false)
    private String username;

    @Column(name="firstName", nullable = false)
    private String firstName;

    @Column(name="lastName", nullable = false)
    private String lastName;

    @NotNull
    @Email
    @Column(name="email", nullable = false)
    private String email;

    private User(String username) {
        this.username = username;
    }

    @NotNull
    @Column(name="password", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.user")
    private List<UserRole> userRoles = new ArrayList<>();

    public static User of (String username){
        return new User(username);
    }

    public static User of(String username, String firstName, String lastName, String email, String password, List<UserRole> roles) {
        return new User(null, username, firstName, lastName, email, password, roles);
    }

    public static User of(String username, String firstName, String lastName, String email, String password) {
        return new User(null, username, firstName, lastName, email, password, null);
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }


}
