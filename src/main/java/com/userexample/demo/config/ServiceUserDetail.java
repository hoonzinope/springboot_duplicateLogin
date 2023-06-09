package com.userexample.demo.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

@Data
public class ServiceUserDetail implements UserDetails, Serializable {

    private static final long serialVersionUID = 174726374856727L;

    private Long seq;	// DB에서 PK 값
    private String loginId;		// 로그인용 ID 값
    private String password;	// 비밀번호
    private Collection<GrantedAuthority> authorities;	//권한 목록

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
