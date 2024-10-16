package com.sprata.memo.controller;

import com.sprata.memo.dto.MemoRequestDto;
import com.sprata.memo.dto.MemoResponseDto;
import com.sprata.memo.service.MemoService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final JdbcTemplate jdbcTemplate;

    public MemoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {

        MemoService memoService = new MemoService(jdbcTemplate);
        return memoService.createMemo(requestDto);

    }



    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        MemoService memoService = new MemoService(jdbcTemplate);
        return memoService.getMemos();

    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {

        MemoService memoService = new MemoService(jdbcTemplate);
        return memoService.updateMemos(id,requestDto);

    }


    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {

        MemoService memoService = new MemoService(jdbcTemplate);
        return memoService.deleteMemos(id);

    }

}