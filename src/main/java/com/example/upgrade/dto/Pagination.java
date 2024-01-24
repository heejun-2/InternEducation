package com.example.upgrade.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pagination {
    private String title; // 검색 조건(제목)
    private int page = 1;

    private int rowCount = 5;   // 한 페이지 당 보여줄 게시글 개수
    private int pageCount = 5;  // 한 블럭에 해당하는 페이지 개수

    private int startPage = 1;  // 한 블럭의 시작 페이지
    private int endPage;        // 한 블럭의 끝 페이지

    private int totalPageCount; // 총 페이지 개수

    private int isPrev;
    private int isNext;

    private int offset;

    public void pageSetting(int totalCount, int page){
        // 전체 마지막 페이지
        this.totalPageCount = (int) Math.ceil(totalCount * 1.0 / rowCount);
        if(totalPageCount == 0) {
            totalPageCount = 1;
        }

        // 마지막 페이지
        this.endPage = (int) Math.ceil(page / (pageCount * 1.0)) * pageCount;
        // 시작 페이지
        this.startPage = this.endPage - pageCount + 1;

        if(endPage > totalPageCount){
            this.endPage = totalPageCount;
        }

        // 이전 다음 버튼
        if(this.startPage > 1){
            this.isPrev = 1;
        }
        if(this.endPage < totalPageCount){
            this.isNext = 1;
        }

        // offset
        this.offset = (page - 1) * rowCount;
    }

}
