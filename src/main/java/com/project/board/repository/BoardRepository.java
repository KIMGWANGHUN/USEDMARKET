package com.project.board.repository;

import com.project.board.entity.Board;
import com.project.board.entity.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
    Page<Board> findBybTitleContaining(String searchKeyword, Pageable pageable);

}
