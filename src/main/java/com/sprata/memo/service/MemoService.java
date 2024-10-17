package com.sprata.memo.service;

import com.sprata.memo.dto.MemoRequestDto;
import com.sprata.memo.dto.MemoResponseDto;
import com.sprata.memo.entity.Memo;
import com.sprata.memo.repository.MemoRepository;


import java.util.List;

public class MemoService {

    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        // RequestDto -> Entity
        Memo memo = new Memo(requestDto);
        // DB 저장
        memoRepository.save(memo);
        // Entity -> ResponseDto
        return new MemoResponseDto(memo);
    }

    public List<MemoResponseDto> getMemos() {
        // DB 조회
        return memoRepository.findAll();
    }

    public Long updateMemos(Long id, MemoRequestDto requestDto) {
        // 해당 메모가 db에 존재하는지 확인
        Memo memo = memoRepository.findById(id);
        if (memo != null) {
            // memo 내용 수정
            memoRepository.update(id, requestDto);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }


    public Long deleteMemos(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = memoRepository.findById(id);
        if (memo != null) {
            memoRepository.delete(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }



}



