package com.example.upgrade.controller;

import com.example.upgrade.SessionConst;
import com.example.upgrade.dto.BoardSaveForm;
import com.example.upgrade.dto.BoardUpdateForm;
import com.example.upgrade.dto.Pagination;
import com.example.upgrade.entity.Board;
import com.example.upgrade.entity.Member;
import com.example.upgrade.entity.UploadFile;
import com.example.upgrade.file.FileStore;
import com.example.upgrade.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final FileStore fileStore;

    @RequestMapping("/boards")
    public String index(@ModelAttribute("board") Pagination sch,
                        @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                        Model model){
        if(member == null){
            return "home";
        }

        model.addAttribute("member", member);

        sch.pageSetting(boardService.titleCount(sch), sch.getPage());

        model.addAttribute("boards", boardService.getBoardList(sch));
        model.addAttribute("pageVo", sch);

        return "boards/index";
    }

    @GetMapping("/boards/{boardId}")
    public String detail(@PathVariable("boardId") int boardId,
                         @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                         Model model){
        if(member == null){
            return "home";
        }

        model.addAttribute("member", member);

        boardService.plusVisitCount(boardId);
        Board board = boardService.getBoard(boardId);
        List<UploadFile> uploadFileList = boardService.getFileList(boardId);

        model.addAttribute("board", board);
        model.addAttribute("fileList", uploadFileList);

        return "boards/detail";
    }

    @GetMapping("/boards/new")
    public String createPage(@ModelAttribute("board") BoardSaveForm dto,
                             @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                             Model model){
        if(member == null){
            return "home";
        }

        model.addAttribute("member", member);

        return "boards/new";
    }

    @PostMapping("/boards/new")
    public String create(@Validated @ModelAttribute("board")BoardSaveForm dto, BindingResult bindingResult, RedirectAttributes redirect) throws IOException {
        if(bindingResult.hasErrors()){
            return "boards/new";
        }

        List<UploadFile> uploadFileList = fileStore.storeFiles(dto.getMultipartFileList());

        boardService.create(dto, uploadFileList);
        redirect.addFlashAttribute("msg", "게시글이 생성되었습니다.");

        return "redirect:/boards";
    }

    @GetMapping("/boards/{boardId}/edit")
    public String editPage(@PathVariable("boardId") int boardId, Model model){
        Board board = boardService.getBoard(boardId);
        List<UploadFile> uploadFileList = boardService.getFileList(boardId);

        model.addAttribute("board", board);
        model.addAttribute("fileList", uploadFileList);

        return "boards/edit";
    }

    @PostMapping("/boards/update")
    public String update(@Validated @ModelAttribute("board")BoardUpdateForm dto, BindingResult bindingResult, RedirectAttributes redirect) throws IOException {
        if(bindingResult.hasErrors()){
            return "boards/edit";
        }

        List<UploadFile> uploadFileList = fileStore.storeFiles(dto.getMultipartFileList());
        log.info("uploadFileList={}", uploadFileList);
        boardService.update(dto, uploadFileList);

        redirect.addFlashAttribute("msg", "게시글이 수정되었습니다");

        return "redirect:/boards/" + dto.getBoardId();
    }

    @GetMapping("/boards/{boardId}/delete")
    public String delete(@PathVariable("boardId")int boardId, RedirectAttributes redirect){
        Board target = boardService.getBoard(boardId);

        if(target != null){
            boardService.deleteFileByBoardId(boardId);
            boardService.delete(boardId);
            redirect.addFlashAttribute("msg", "게시글이 삭제되었습니다.");
        }

        return "redirect:/boards";
    }

    @ResponseBody
    @PostMapping("/board/delete")
    public List<String> choiceDelete(@RequestBody List<String> boardIdArray) {
        log.info("boardIdArray={}", boardIdArray);
        boardService.choiceDelete(boardIdArray);

        return boardIdArray;
    }

    @GetMapping("/boards/deleteAll")
    public String deleteAll(RedirectAttributes redirect){
        int count = boardService.countBoard();

        if(count > 0){
            boardService.deleteAll();
            redirect.addFlashAttribute("msg", "모든 게시글이 삭제되었습니다.");
        }
        else{
            redirect.addFlashAttribute("msg", "등록된 게시글이 없습니다.");
        }

        return "redirect:/boards";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") int fileId) throws MalformedURLException {
        UploadFile file = boardService.getFile(fileId);
        String uploadFileName = file.getUploadFileName();
        String storeFileName = file.getStoreFileName();

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        String encodeUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodeUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    @GetMapping("/boards/{fileId}/fileDelete")
    public String fileDelete(@PathVariable("fileId") int fileId,
                             @RequestParam(name = "boardId") int boardId, RedirectAttributes redirect){
        boardService.fileDelete(fileId);

        redirect.addFlashAttribute("msg", "첨부파일이 삭제되었습니다.");

        return "redirect:/boards/" + boardId;
    }


    @PostMapping("/index.do")
    @ResponseBody
    public Board ajax(@RequestBody Map<String, String> vo){
        log.info("ket={}", vo.keySet());
        log.info("values={}", vo.values());
        Board board = new Board();
        board.setName("heejun");
        return board;
    }

    @PostMapping("/ajax1.do")
    @ResponseBody
    public String ajax2(@RequestParam(name = "name") String name){
        log.info("name={}", name);

        return name;
    }
}
