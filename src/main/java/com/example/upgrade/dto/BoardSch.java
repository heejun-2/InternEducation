package com.example.upgrade.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardSch {
    private String title; // 검색 조건(제목)

    private int rowCount = 5;   // 한 페이지 당 보여줄 게시글 개수
    private int pageCount = 3;  // 한 블럭에 해당하는 페이지 개수

    private int startPage = 1;  // 한 블럭의 시작 페이지
    private int endPage;        // 한 블럭의 끝 페이지

    private int totalPageCount; // 총 페이지 개수

    private int isPrev = 0;
    private int isNext = 0;

    private int offset;

    public void pageSetting(int totalCount, int page){
        // 총 페이지 개수 구하기
        setTotalPageCount(totalCount, rowCount);

        // 한 블럭의 첫 페이지 구하기
        setStartPage(startPage, page, pageCount);

        // 한 블럭의 끝 페이지 구하기
        setEndPage(startPage, pageCount, totalPageCount);

        // 이전 블록 버튼 유무 판별하기
        isPrev(page, pageCount);

        // 다음 블록 버튼 유무 판별하기
        isNext(endPage, totalPageCount);

        // offset 구하기
        setOffset(page, rowCount);
    }

    private void setTotalPageCount(int totalCount, int rowCount){
        this.totalPageCount = (int) Math.ceil(totalCount * 1.0 / rowCount);
    }
    
    private void setStartPage(int startPage, int page, int pageCount){
        this.startPage = startPage + (((page - startPage) / pageCount) * pageCount);
    }

    private void setEndPage(int startPage, int pageCount, int totalPageCount){
        if(totalPageCount == 0) totalPageCount = 1;
        this.endPage = Math.min(((startPage - 1) + pageCount), totalPageCount);
    }

    private void isPrev(int page, int pageCount){
        this.isPrev = 1 < ((page * 1.0) / pageCount) ? 1 : 0;
    }

    private void isNext(int endPage, int totalPageCount){
        this.isNext = endPage < totalPageCount ? 1 : 0;
    }

    private void setOffset(int page, int rowCount){
        this.offset = (page - 1) * rowCount;
    }

}
