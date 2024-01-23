package com.example.upgrade.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberUpdateForm {
    private int memberId;

    @Pattern(regexp = "^[가-힣]{2,4}$", message = "한글 2~4자 이내입니다.")
    private String name;

    @Pattern(regexp = "^[a-z]{1}[a-z0-9]{5,20}+$", message = "영문 숫자 조합 6~20자를 사용하세요.")
    private String id;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String originalPassword;

    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9]+@[0-9a-zA-Z]+\\.[a-z]+$", message = "이메일 형식으로 입력해주세요.")
    private String email;

    @Pattern(regexp = "^010-([0-9]{3,4})-([0-9]{4})+$", message = "010-1234-5678 형식으로 입력해주세요.")
    private String phoneNumber;
}
