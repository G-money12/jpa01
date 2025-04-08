package org.pgm.jpa01.repository;

import org.pgm.jpa01.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // select * from tbl_board where title like %keyword%;
    @Query("select b from Board b where b.title like concat('%', :keyword, '%') ")
    Page<Board> searchTitle(String keyword, Pageable pageable);
}
