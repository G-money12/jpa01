package org.pgm.jpa01.controller;

import org.pgm.jpa01.domain.Board;
import org.pgm.jpa01.dto.BoardDTO;
import org.pgm.jpa01.dto.PageRequestDTO;
import org.pgm.jpa01.dto.PageResponseDTO;
import org.pgm.jpa01.repository.BoardRepository;
import org.pgm.jpa01.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @GetMapping("list")
    public void list (PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<BoardDTO> responseDTO=boardService.list(pageRequestDTO);
        model.addAttribute("responseDTO",responseDTO);
        model.addAttribute("pageRequestDTO",pageRequestDTO);
//         List<BoardDTO> boardList = boardService.readAllBoards();
//         model.addAttribute("boardList", boardList);
    }
    @GetMapping("/register")
    public void register(){
    }
    @PostMapping("/register")
    public String register(BoardDTO boardDTO){
        boardService.registerBoard(boardDTO);
        return "redirect:/board/list";
    }
    @GetMapping({"/read", "/modify"})
    public void read_modify(Long bno, PageRequestDTO pageRequestDTO, Model model){
        BoardDTO boardDTO = boardService.readBoard(bno);
        model.addAttribute("board", boardDTO);
    }
    @GetMapping("/remove")
    public String remove(Long bno){
        boardService.deleteBoard(bno);
        return "redirect:/board/list";
    }
    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO, PageRequestDTO pageRequestDTO){
        boardService.updateBoard(boardDTO);
        return "redirect:/board/read?bno="+boardDTO.getBno();
    }

}
