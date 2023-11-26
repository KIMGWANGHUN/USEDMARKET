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

    //게시판 글작성
    public void write(Board board) {
        boardRepository.save(board);
    }

    //게시판 리스트
    public List<Board> boardList(){
        return boardRepository.findAll();
    }

    //게시글 페이지
    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }

    //게시글 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }

    //게시글 수정
    public void boardModify(){

    }



}
