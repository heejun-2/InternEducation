package com.example.upgrade.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Board {
    private int boardId;

    private String title;
    private String content;
    private String name;
    private int visitCount;
    private int memberId;
    private Date regdate;

    private List<UploadFile> uploadFileList;
}
