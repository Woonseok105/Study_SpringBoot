package com.example.study_springboot.global.security.auth;

import com.example.study_springboot.domain.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class AuthDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    //사용자에게 부여된 권한을 반환합니다.

    @Override
    public String getPassword() {
        return null;
    }
    //사용자를 인증하는 데 사용된 암호를 반환합니다.

    @Override
    public String getUsername() {
        return user.getAccountId();
    }
    //사용자를 인증하는 데 사용된 사용자 이름을 반환합니다.

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //사용자의 계정이 만료되었는지 여부를 나타냅니다.

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //사용자가 잠겨 있는지 또는 잠금 해제되어 있는지 나타냅니다.

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //사용자의 자격 증명(암호)이 만료되었는지 여부를 나타냅니다.

    @Override
    public boolean isEnabled() {
        return true;
    }
    //사용자가 활성화되었는지 비활성화되었는지 여부를 나타냅니다.

}
