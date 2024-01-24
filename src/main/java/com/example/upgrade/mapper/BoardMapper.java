package com.example.upgrade.mapper;

import com.example.upgrade.dto.Pagination;
import com.example.upgrade.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> getBoardList(Pagination sch);

    int countBoard();

    int titleCount(Pagination sch);

    Board getBoard(int BoardId);

    void plusVisitCount(int boardId);

    void create(Board board);

    void update(Board board);

    void delete(int boardId);

    void deleteAll();

    void fileDelete(int BoardId);


}
