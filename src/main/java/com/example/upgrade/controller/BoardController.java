package com.example.upgrade.controller;

import com.example.upgrade.SessionConst;
import com.example.upgrade.dto.BoardSaveForm;
import com.example.upgrade.dto.BoardSch;
import com.example.upgrade.dto.BoardUpdateForm;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import javax.naming.Binding;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final FileStore fileStore;

    @RequestMapping("/boards")
    public String index(@ModelAttribute("board") BoardSch sch,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                        Model model){
        if(member == null){
            return "home";
        }

        model.addAttribute("member", member);

        sch.pageSetting(boardService.titleCount(sch), page);

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

        model.addAttribute("board", board);

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

        UploadFile uploadFile = fileStore.storeFile(dto.getAttachFile());

        boardService.create(dto, uploadFile);
        redirect.addFlashAttribute("msg", "게시글이 생성되었습니다.");

        return "redirect:/boards";
    }

    @GetMapping("/boards/{boardId}/edit")
    public String editPage(@PathVariable("boardId") int boardId, Model model){
        Board board = boardService.getBoard(boardId);

        model.addAttribute("board", board);

        return "boards/edit";
    }

    @PostMapping("/boards/update")
    public String update(@Validated @ModelAttribute("board")BoardUpdateForm dto, BindingResult bindingResult, RedirectAttributes redirect) throws IOException {
        if(bindingResult.hasErrors()){
            return "boards/edit";
        }

        UploadFile uploadFile = fileStore.storeFile(dto.getAttachFile());

        boardService.update(dto, uploadFile);
        redirect.addFlashAttribute("msg", "게시글이 수정되었습니다");

        return "redirect:/boards/" + dto.getBoardId();
    }

    @GetMapping("/boards/{boardId}/delete")
    public String delete(@PathVariable("boardId")int boardId, RedirectAttributes redirect){
        Board target = boardService.getBoard(boardId);

        if(target != null){
            boardService.delete(boardId);
            redirect.addFlashAttribute("msg", "게시글이 삭제되었습니다.");
        }

        return "redirect:/boards";
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

    @GetMapping("/download/{boardId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("boardId") int boardId) throws MalformedURLException {
        Board board = boardService.getBoard(boardId);
        String uploadFileName = board.getUploadFileName();
        String storeFileName = board.getStoreFileName();
        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        String encodeUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodeUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    @GetMapping("/boards/{boardId}/fileDelete")
    public String fileDelete(@PathVariable("boardId") int boardId, RedirectAttributes redirect){
        boardService.fileDelete(boardId);

        redirect.addFlashAttribute("msg", "첨부파일이 삭제되었습니다.");

        return "redirect:/boards/" + boardId;
    }

}
