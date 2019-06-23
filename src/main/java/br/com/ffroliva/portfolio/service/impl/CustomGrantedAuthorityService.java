package br.com.ffroliva.portfolio.service.impl;

import br.com.ffroliva.portfolio.exception.ResourceNotFoundException;
import br.com.ffroliva.portfolio.model.Role;
import br.com.ffroliva.portfolio.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomGrantedAuthorityService {

    private final RoleRepository roleRepository;

    List<GrantedAuthority> loadByUsername(String username) {
        final Optional<List<Role>> roles = roleRepository.findRolesByUsername(username);
        return roles.orElseThrow(() -> new ResourceNotFoundException(
                Role.class.getSimpleName(),
                "üsername"
                , username))
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getName().toString()))
                .collect(Collectors.toList());

    }
}
