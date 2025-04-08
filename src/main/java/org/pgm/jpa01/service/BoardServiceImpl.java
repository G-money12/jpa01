package org.pgm.jpa01.service;

import lombok.extern.log4j.Log4j2;
import org.pgm.jpa01.domain.Board;
import org.pgm.jpa01.dto.BoardDTO;
import org.pgm.jpa01.dto.PageRequestDTO;
import org.pgm.jpa01.dto.PageResponseDTO;
import org.pgm.jpa01.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public void registerBoard(BoardDTO boardDTO) {
        Board board = dtoToEntity(boardDTO);
        boardRepository.save(board);
    }

    @Override
    public BoardDTO readBoard(Long id) {
        Board board = boardRepository.findById(id).get();
        board.updateReadcount();
        boardRepository.save(board);
        BoardDTO boardDTO = entityToDto(board);
        return boardDTO;
    }

    @Override
    public void updateBoard(BoardDTO boardDTO) {
        Board board1=boardRepository.findById(boardDTO.getBno()).get();
        board1.change(boardDTO.getTitle(), boardDTO.getContent());
//        board1.setContent(board.getContent());
//        board1.setAuthor(board.getAuthor());
//        board1.setTitle(board.getTitle());
        boardRepository.save(board1);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("bno");
        //Page<Board> result = boardRepository.findAll(pageable);
        Page<Board> result=boardRepository.searchTitle(pageRequestDTO.getKeyword(), pageable);

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> entityToDto(board))
                .collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

//    @Override
//    public List<BoardDTO> readAllBoards() {
//        Pageable pageable = PageRequest.of(1, 5, Sort.by("bno").descending());          // 페이징 처리 (0, 10
//        List<Board> boards = boardRepository.findAll(pageable).getContent();
//
//        List<BoardDTO> boardDTOS =boards.stream()
//                .map(board -> entityToDto(board))
//                .collect(Collectors.toList());

//        List<BoardDTO> boardDTOList = new ArrayList<>();
//        for (Board board : boards) {
//            boardDTOList.add(boardToBoardDTO(board));
//        }
//
//        return boardDTOS;
//    }
}
