package com.flashcard.restservice.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "username", nullable = false, length = Integer.MAX_VALUE)
    private String username;

    @NotNull
    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @NotNull
    @Column(name = "password", nullable = false, length = Integer.MAX_VALUE)
    private String password;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "account_expiry_date")
    private Instant accountExpiryDate;

    @ColumnDefault("false")
    @Column(name = "locked_status")
    private Boolean lockedStatus;

    @Column(name = "password_expiry_date")
    private Instant passwordExpiryDate;

    @ColumnDefault("true")
    @Column(name = "enabled")
    private Boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getAccountExpiryDate() {
        return accountExpiryDate;
    }

    public void setAccountExpiryDate(Instant accountExpiryDate) {
        this.accountExpiryDate = accountExpiryDate;
    }

    public Boolean getLockedStatus() {
        return lockedStatus;
    }

    public void setLockedStatus(Boolean lockedStatus) {
        this.lockedStatus = lockedStatus;
    }

    public Instant getPasswordExpiryDate() {
        return passwordExpiryDate;
    }

    public void setPasswordExpiryDate(Instant passwordExpiryDate) {
        this.passwordExpiryDate = passwordExpiryDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_USER");
    }
    @Override
    public boolean isAccountNonExpired() {
        return accountExpiryDate == null || accountExpiryDate.isAfter(Instant.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !lockedStatus;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return passwordExpiryDate == null || passwordExpiryDate.isAfter(Instant.now());
    }

    @Override
    public boolean isEnabled() {
        return enabled;  // Check if the account is enabled
    }
}