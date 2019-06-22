package br.com.ffroliva.portfolio.repository;

import br.com.ffroliva.portfolio.model.UserRole;
import br.com.ffroliva.portfolio.model.id.UserRoleId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, UserRoleId> {

}
