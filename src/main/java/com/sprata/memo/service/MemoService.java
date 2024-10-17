package com.sprata.memo.service;

import com.sprata.memo.dto.MemoRequestDto;
import com.sprata.memo.dto.MemoResponseDto;
import com.sprata.memo.entity.Memo;
import com.sprata.memo.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// @Component // 빈 객체로 등록하여 의존성 주입이 필요할때 @Autowird를 달아서 알려준다,
            // 스프링 자체에 Component Scane 이 있음.
            // ioc 컨테이너를 통해 관리가 되는 빈 클래스만 가능하다
            // ApplicationContext 를 통해 수동으로 Bean 이름,클래스 형식으로 가져올수 있다.

@Service // Service 클래스 안에 Component가 있음

public class MemoService { // MemoService


    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) { // 생성자 주입을 주로 사용하고 하나의 생성자만 존재할 경우 @Autowird를 생략 가능하다
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



