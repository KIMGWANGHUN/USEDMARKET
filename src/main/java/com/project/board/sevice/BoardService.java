package com.project.board.sevice;

import com.project.board.entity.Board;
import com.project.board.entity.Member;
import com.project.board.repository.BoardRepository;
import com.project.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //게시판 글작성
    public void write(Board board){
        boardRepository.save(board);
    }

    //게시판 글작성(파일추가)
    public void write(Board board, MultipartFile file) throws Exception{
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        board.setFileName(fileName);
        board.setFilePath("/files/" + fileName);
        boardRepository.save(board);
    }

    //게시판 리스트(페이징처리X)
/*    public List<Board> boardList(){
        return boardRepository.findAll();
    }*/

    //게시판 리스트(페이징처리O)
    public Page<Board> boardList(Pageable pageable){

        return boardRepository.findAll(pageable);
    }

    //게시글 페이지
    public Board boardView(Integer id){
        Board board = boardRepository.findById(id).get();
        board.setBViews(board.getBViews() +1);
        return board;
    }

    //게시글 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }


}
