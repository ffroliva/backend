package br.com.ffroliva.portfolio.repository;

import br.com.ffroliva.portfolio.model.UserRole;
import br.com.ffroliva.portfolio.model.UserRoleId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRoleRepositorty extends PagingAndSortingRepository<UserRole, UserRoleId> {
}
