package br.com.ffroliva.portfolio.repository;

import br.com.ffroliva.portfolio.model.Role;
import br.com.ffroliva.portfolio.model.enums.RoleName;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

    Optional<Role> findByName(RoleName name);

//    @Query( value = "SELECT role FROM Role role join role.userRoles userRole " +
//            "WHERE userRole.id.user.username = :username")
    @Query( value = "SELECT userRole.id.role FROM UserRole userRole " +
            "WHERE userRole.id.user.username = :username")
   Optional<List<Role>> loadRolesByUsername(@Param("username") String username);
}
