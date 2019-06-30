package br.com.ffroliva.portfolio.model;

import br.com.ffroliva.portfolio.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@ToString
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
public class User extends DateAudit<Integer> {

	private static final long serialVersionUID = -4373524336039096881L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Integer id;

    @NotNull
    @Column(name="username", nullable = false)
    private String username;

    @Column(name="firstName", nullable = false)
    private String firstName;

    @Column(name="lastName", nullable = false)
    private String lastName;

    @Email
    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.user")
    private List<UserRole> userRoles = new ArrayList<>();

    private User(String username) {
        this.username = username;
    }
    
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
