package com.example.upgrade.service;

import com.example.upgrade.Encryption;
import com.example.upgrade.dto.MemberSaveForm;
import com.example.upgrade.dto.MemberUpdateForm;
import com.example.upgrade.entity.Member;
import com.example.upgrade.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;
    public void save(MemberSaveForm dto) {
        Member member = new Member();
        member.setId(dto.getId());
        // 입력한 비밀번호
        member.setOriginalPassword(dto.getOriginalPassword());
        // 비밀번호 암호화 처리
        member.setPassword(Encryption.encode(dto.getOriginalPassword()));
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setPhoneNumber(dto.getPhoneNumber());
        member.setRole(dto.getRole());

        memberMapper.save(member);
    }

    public Member getMember(int memberId) {

        return memberMapper.getMember(memberId);
    }

    public void memberUpdate(MemberUpdateForm dto) {
        Member member = new Member();
        member.setMemberId(dto.getMemberId());
        member.setId(dto.getId());
        member.setOriginalPassword(dto.getOriginalPassword());
        // 비밀번호 암호화 처리 후 저장
        member.setPassword(Encryption.encode(dto.getOriginalPassword()));
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setPhoneNumber(dto.getPhoneNumber());

        memberMapper.memberUpdate(member);
    }

    public void memberDelete(int memberId) {
        memberMapper.memberDelete(memberId);
    }
}
