package com.example.KinoLargo_api.controllers;

import com.example.KinoLargo_api.filters.JwtAuthenticationFilter;
import com.example.KinoLargo_api.models.dto.auth.AdminUserDTO;
import com.example.KinoLargo_api.models.dto.auth.PublicUserDTO;
import com.example.KinoLargo_api.services.UserService;
import com.example.KinoLargo_api.services.impl.security.AuthenticationServiceImpl;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final AuthenticationServiceImpl authenticationService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AdminUserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}/admin")
    public ResponseEntity<AdminUserDTO> getByIdAdmin(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getByIdAdmin(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @RateLimiter(name = "general_api_rate_limiter")
    public ResponseEntity<AdminUserDTO> update(@PathVariable("id") UUID id, @RequestBody AdminUserDTO userDTO, HttpServletRequest httpServletRequest) {
        PublicUserDTO user = (PublicUserDTO) httpServletRequest.getAttribute(JwtAuthenticationFilter.USER_KEY);
        return ResponseEntity.ok(userService.updateUser(id, userDTO, user));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id, HttpServletRequest httpServletRequest) {
        PublicUserDTO user = (PublicUserDTO) httpServletRequest.getAttribute(JwtAuthenticationFilter.USER_KEY);
        userService.deleteUserById(id, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<PublicUserDTO> getMe(@RequestHeader("Authorization") String auth){
        return ResponseEntity.ok(authenticationService.me(auth));
    }

}
