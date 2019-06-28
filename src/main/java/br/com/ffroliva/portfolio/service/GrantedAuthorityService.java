package br.com.ffroliva.portfolio.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public interface GrantedAuthorityService {

    List<GrantedAuthority> loadByUsername(String username);
}
