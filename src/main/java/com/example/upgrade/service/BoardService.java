package com.example.upgrade.service;

import com.example.upgrade.dto.BoardSaveForm;
import com.example.upgrade.dto.BoardUpdateForm;
import com.example.upgrade.dto.Pagination;
import com.example.upgrade.entity.Board;
import com.example.upgrade.entity.UploadFile;
import com.example.upgrade.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<Board> getBoardList(Pagination sch){
        if(sch.getTitle() == null) sch.setTitle("");

        return boardMapper.getBoardList(sch);
    }

    public int countBoard() {
        return boardMapper.countBoard();
    }
    public int titleCount(Pagination boardSch){
        if(boardSch.getTitle() == null) boardSch.setTitle("");

        return boardMapper.titleCount(boardSch);
    }

    public Board getBoard(int boardId){
        return boardMapper.getBoard(boardId);
    }

    public void plusVisitCount(int boardId){
        boardMapper.plusVisitCount(boardId);
    }


    public void create(BoardSaveForm dto, UploadFile uploadFile) {
        Board board = new Board();
        board.setName(dto.getName());
        board.setContent(dto.getContent());
        board.setVisitCount(0);
        board.setTitle(dto.getTitle());
        board.setMemberId(dto.getMemberId());

        if(uploadFile != null){
            board.setUploadFileName(uploadFile.getUploadFileName());
            board.setStoreFileName(uploadFile.getStoreFileName());
        }
        else{
            board.setUploadFileName("");
            board.setStoreFileName("");
        }
        // list 크기만큼 파일저장

        boardMapper.create(board);
    }

    public void update(BoardUpdateForm dto, UploadFile uploadFile) {
        Board board = new Board();
        board.setBoardId(dto.getBoardId());
        board.setName(dto.getName());
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setVisitCount(dto.getVisitCount());

        if(uploadFile != null){
            board.setUploadFileName(uploadFile.getUploadFileName());
            board.setStoreFileName(uploadFile.getStoreFileName());
        }
        else{
            board.setUploadFileName(dto.getUploadFileName());
            board.setStoreFileName(dto.getStoreFileName());
        }

        boardMapper.update(board);
    }

    public void delete(int boardId){
        boardMapper.delete(boardId);
    }

    public void choiceDelete(List<String> boardIdArray) {
        for (String s : boardIdArray) {
            int boardId = Integer.parseInt(s);
            boardMapper.delete(boardId);
        }
    }

    public void deleteAll(){
        boardMapper.deleteAll();
    }

    public void fileDelete(int boardId){
        boardMapper.fileDelete(boardId);
    }


}
