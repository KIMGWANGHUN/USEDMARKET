package com.project.board.repository;

import com.project.board.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface NoticeRepository extends JpaRepository<Notice,Long> {

}
