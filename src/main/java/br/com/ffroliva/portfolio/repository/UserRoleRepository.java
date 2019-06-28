package br.com.ffroliva.portfolio.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.ffroliva.portfolio.model.UserRole;
import br.com.ffroliva.portfolio.model.id.UserRoleId;

public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, UserRoleId> {

}
