package com.example.upgrade.entity;

import lombok.Data;

@Data
public class Member {
    private int memberId;
    private String id;
    private String password;    // 암호화 후 비밀번호
    private String originalPassword;    // 암호화 하기 전 비밀번호
    private String name;
    private String email;
    private String phoneNumber;
    private String role;
}
