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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //게시판 글작성(파일추가)
    public void write(Board board, MultipartFile file) throws Exception {
        //프로젝트 경로를 담아줌(경로지정)
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img";

        //랜덤으로 이름을 생성(식별자)
        UUID uuid = UUID.randomUUID();

        //식별자 + 파일이름 = 최종 파일이름
        String fileName = uuid + "_" + file.getOriginalFilename();

        //파일을 projectPath 경로와 fileName 이름으로 담는다(껍데기 생성)
        File saveFile = new File(projectPath, fileName);

        //
        file.transferTo(saveFile);

        //생성된 fileName을 넣는다
        board.setFileName(fileName);

        //생성된 filePath를 넣는다(서버에서 접근할 땐 static 밑에 있는 부분은 아래 경로로 접근가능)
        board.setFilePath("/img/" + fileName);

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
        board.setBViews(board.getBViews()+1);
        return board;
    }

    //게시글 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }

}
