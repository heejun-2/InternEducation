package com.example.upgrade.mapper;

import com.example.upgrade.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface LoginMapper {

    Member login(@Param("id") String id, @Param("password") String password);
}
