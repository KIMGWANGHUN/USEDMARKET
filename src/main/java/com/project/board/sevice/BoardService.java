package com.project.board.sevice;

import com.project.board.entity.Board;
import com.project.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> boardList() {
        return boardRepository.findAll();
    }

}
