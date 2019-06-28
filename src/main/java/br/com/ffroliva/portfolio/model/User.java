package br.com.ffroliva.portfolio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import br.com.ffroliva.portfolio.model.audit.DateAudit;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class User extends DateAudit<Long> {

	private static final long serialVersionUID = -4373524336039096881L;

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
    
    @Override
    public Long getId(){
    	return id;
    }

}
