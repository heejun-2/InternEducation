package com.example.upgrade.dto;

import com.example.upgrade.entity.Board;
import com.example.upgrade.entity.UploadFile;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    private List<MultipartFile> multipartFileList;

}
