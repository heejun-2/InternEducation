package com.example.upgrade.service;

import com.example.upgrade.dto.BoardSaveForm;
import com.example.upgrade.dto.BoardUpdateForm;
import com.example.upgrade.dto.Pagination;
import com.example.upgrade.entity.Board;
import com.example.upgrade.entity.UploadFile;
import com.example.upgrade.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BoardService {
    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<Board> getBoardList(Pagination sch){
        if(sch.getTitle() == null) sch.setTitle("");

        return boardMapper.getBoardList(sch);
    }
    public Board getBoard(int boardId){
        return boardMapper.getBoard(boardId);
    }

    public List<UploadFile> getFileList(int boardId) {
        return boardMapper.getFileList(boardId);
    }

    public UploadFile getFile(int fileId){
        return boardMapper.getFile(fileId);
    }

    public int countBoard() {
        return boardMapper.countBoard();
    }
    public int titleCount(Pagination boardSch){
        if(boardSch.getTitle() == null) boardSch.setTitle("");

        return boardMapper.titleCount(boardSch);
    }

    public void plusVisitCount(int boardId){
        boardMapper.plusVisitCount(boardId);
    }


    public void create(BoardSaveForm dto, List<UploadFile> uploadFileList) {
        Board board = new Board();
        board.setName(dto.getName());
        board.setContent(dto.getContent());
        board.setVisitCount(0);
        board.setTitle(dto.getTitle());
        board.setMemberId(dto.getMemberId());
        board.setRegdate(dto.getRegdate());

        boardMapper.create(board);

        // list 크기만큼 파일저장
        for (UploadFile uploadFile : uploadFileList) {
            if(uploadFile != null) {
                uploadFile.setBoardId(board.getBoardId());
                boardMapper.fileUpload(uploadFile);
            }
            else{
                return;
            }
        }
    }

    public void update(BoardUpdateForm dto, List<UploadFile> uploadFileList) {
        Board board = new Board();
        board.setBoardId(dto.getBoardId());
        board.setName(dto.getName());
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setVisitCount(dto.getVisitCount());

        boardMapper.update(board);


        for (UploadFile uploadFile : uploadFileList) {
            if(uploadFile != null){
                uploadFile.setBoardId(dto.getBoardId());

                boardMapper.fileUpload(uploadFile);
            }
            else{
                return;
            }
        }

    }

    public void delete(int boardId){
        boardMapper.delete(boardId);
    }

    public void deleteFileByBoardId(int boardId){
        boardMapper.deleteFileByBoardId(boardId);
    }
    public void choiceDelete(List<String> boardIdArray) {
        for (String s : boardIdArray) {
            int boardId = Integer.parseInt(s);
            boardMapper.deleteFileByBoardId(boardId);
            boardMapper.delete(boardId);
        }
    }

    public void deleteAll(){
        boardMapper.deleteAll();
    }

    public void fileDelete(int fileId){
        boardMapper.fileDelete(fileId);
    }



}
