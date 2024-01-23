package com.example.upgrade.mapper;

import com.example.upgrade.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Member getMember(int memberId);

    void memberUpdate(Member member);

    void memberDelete(int memberId);


}
