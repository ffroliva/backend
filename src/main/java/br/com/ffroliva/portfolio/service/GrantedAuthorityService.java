package br.com.ffroliva.portfolio.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface GrantedAuthorityService {

    List<GrantedAuthority> loadByUsername(String username);
}
