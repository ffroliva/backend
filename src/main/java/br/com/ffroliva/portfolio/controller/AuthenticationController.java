package br.com.ffroliva.portfolio.controller;

import br.com.ffroliva.portfolio.exception.AppException;
import br.com.ffroliva.portfolio.model.Role;
import br.com.ffroliva.portfolio.model.User;
import br.com.ffroliva.portfolio.model.UserRole;
import br.com.ffroliva.portfolio.model.id.UserRoleId;
import br.com.ffroliva.portfolio.payload.ApiResponse;
import br.com.ffroliva.portfolio.payload.JwtAuthenticationResponse;
import br.com.ffroliva.portfolio.payload.LoginRequest;
import br.com.ffroliva.portfolio.payload.SignupRequest;
import br.com.ffroliva.portfolio.repository.RoleRepository;
import br.com.ffroliva.portfolio.repository.UserRepository;
import br.com.ffroliva.portfolio.repository.UserRoleRepository;
import br.com.ffroliva.portfolio.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static br.com.ffroliva.portfolio.config.properties.MessageProperty.*;
import static br.com.ffroliva.portfolio.model.enums.RoleName.ROLE_USER;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<ApiResponse>(
            		new ApiResponse(false, USERNAME_ALREADY_TAKEN.message()),
                    BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<ApiResponse>(
            		new ApiResponse(false, EMAIL_ALREADY_IN_USE.message()),
                    BAD_REQUEST);
        }

        // Encoding password
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        // Creating user's account
        User user = User.of(
                signUpRequest.getUsername(),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getEmail(),
                encodedPassword);
        User result = userRepository.save(user);

        Role role = roleRepository
                .findByName(ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        UserRole userRole = new UserRole(new UserRoleId(result, role));
        userRoleRepository.save(userRole);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity
                .created(location)
                .body(new ApiResponse(true, USER_REGISTERED_SUCCESSFULLY.message()));
    }
}