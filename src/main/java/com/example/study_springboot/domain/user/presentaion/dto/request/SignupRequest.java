package com.example.study_springboot.domain.user.presentaion.dto.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SignupRequest {

    @NotNull
    @Size(max = 12, message = "아이디는 12자 아래입니다")
    private String accountId;

    @NotNull
    @Size(max = 24, message = "비밀번호는 24자 아래입니다")
    private String password;
}
