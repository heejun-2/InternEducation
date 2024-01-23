package com.example.upgrade.service;

import com.example.upgrade.Encryption;
import com.example.upgrade.entity.Member;
import com.example.upgrade.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginMapper loginMapper;

    public Member login(String id, String password){
        if(loginMapper.login(id, password) == null){
            return null;
        }

        return loginMapper.login(id, password);
    }
}
