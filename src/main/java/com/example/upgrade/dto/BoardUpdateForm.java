package com.example.upgrade.dto;

import com.example.upgrade.entity.Board;
import com.example.upgrade.entity.UploadFile;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class BoardUpdateForm {
    private int boardId;

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private String name;
    private int visitCount;
    private int memberId;

    // 실제 업로드한 파일명
    private String uploadFileName;
    // 서버에서 관리하는 파일명
    private String storeFileName;

    private MultipartFile attachFile;

}