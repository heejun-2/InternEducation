package com.example.upgrade.entity;

import lombok.Data;

@Data
public class Board {
    private int boardId;

    private String title;
    private String content;
    private String name;
    private int visitCount;
    private int memberId;

    // 실제 업로드한 파일명
    private String uploadFileName;
    // 서버에서 관리하는 파일명
    private String storeFileName;

    private UploadFile attachFile;
}
