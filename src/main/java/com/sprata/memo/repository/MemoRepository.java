package com.sprata.memo.repository;


import com.sprata.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findAllByOrderByModifiedAtDesc(); // Desc 내림차순

    List<Memo> finAllByContentsContainsOrderByModifiedAtDesc(String keyword);

}

