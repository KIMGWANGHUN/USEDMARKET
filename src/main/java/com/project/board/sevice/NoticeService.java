package com.project.board.sevice;

import com.project.board.entity.Notice;
import com.project.board.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final EntityManager entityManager;

    public Notice createNotice (Notice notice){
        return noticeRepository.save(notice);
    }
    public Optional<Notice> getNotice(Long id) {
        return noticeRepository.findById(id);
    }
    public List<Notice> getNoticeList(){
        return noticeRepository.findAll();
    }
}
