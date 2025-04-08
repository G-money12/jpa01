package org.pgm.jpa01.service;

import org.pgm.jpa01.domain.Board;
import org.pgm.jpa01.dto.BoardDTO;
import org.pgm.jpa01.dto.PageRequestDTO;
import org.pgm.jpa01.dto.PageResponseDTO;

import java.util.List;

public interface BoardService {
    void registerBoard(BoardDTO boardDTO);
    BoardDTO readBoard(Long id);
    void updateBoard(BoardDTO boardDTO);
    void deleteBoard(Long id);
    //List<BoardDTO> readAllBoards();
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
    default Board dtoToEntity(BoardDTO boardDTO){
        Board board=Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .author(boardDTO.getAuthor())
                .build();
        return board;
    }
    default BoardDTO entityToDto(Board board){
        BoardDTO boardDTO=BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .author(board.getAuthor())
                .readcount(board.getReadcount())
                .regDate(board.getRegDate())
                .updateDate(board.getUpdateDate())
                .build();
        return boardDTO;
    }
}
