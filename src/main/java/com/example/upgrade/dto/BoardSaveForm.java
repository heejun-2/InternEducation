package com.example.upgrade.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardSaveForm {
    private String name;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private int visitCount;
    private int memberId;
    private Date regdate;

    private List<MultipartFile> multipartFileList;
}
