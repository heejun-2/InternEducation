package com.example.upgrade.mapper;

import com.example.upgrade.dto.Pagination;
import com.example.upgrade.entity.Board;
import com.example.upgrade.entity.UploadFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> getBoardList(Pagination sch);
    Board getBoard(int BoardId);

    List<UploadFile> getFileList(int BoardId);
    UploadFile getFile(int fileId);
    int countBoard();

    int titleCount(Pagination sch);


    void plusVisitCount(int boardId);

    void create(Board board);

    void fileUpload(UploadFile uploadFile);

    void update(Board board);


    void delete(int boardId);

    void deleteFileByBoardId(int boardId);

    void deleteAll();

    void fileDelete(int fileId);


}
