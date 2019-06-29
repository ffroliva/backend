package br.com.ffroliva.portfolio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.ffroliva.portfolio.model.Role;
import br.com.ffroliva.portfolio.model.enums.RoleName;

public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

    Optional<Role> findByName(RoleName name);

    @Query( value = "SELECT role FROM Role role join role.userRoles userRole " +
            "WHERE userRole.id.user.username = :username")
    Optional<List<Role>> loadRolesByUsername(@Param("username") String username);
}
