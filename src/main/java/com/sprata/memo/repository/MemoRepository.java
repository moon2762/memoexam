package com.sprata.memo.repository;


import com.sprata.memo.entity.Memo;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MemoRepository extends JpaRepository<Memo, Long> {


    void createMemo(EntityManager em);
}

