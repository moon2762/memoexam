package com.sprata.memo.controller;

import com.sprata.memo.dto.MemoRequestDto;
import com.sprata.memo.dto.MemoResponseDto;
import com.sprata.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();



    @PostMapping("memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){
    // RequestDto -> Entity
    Memo memo = new Memo(requestDto);

    // Memo Max ID Check
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet())+1 : 1;
        // 만약 memoList에 데이터가 있으면 가장 큰 키 값에 1을 더한 값이 maxId가 되고,
        // memoList가 비어 있으면 maxId는 1

        //이 코드는 주로 memoList와 같은 컬렉션에서 새로운 항목을 추가할 때 중복되지 않는 고유한 ID를 생성하기 위해 사용


        memo.setId(maxId);

        // DB 저장
        memoList.put(memo.getId(), memo);

        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;

    }

    @GetMapping("memos")
    public List<MemoResponseDto> getMemos(){
    // Map To List
    List<MemoResponseDto> responseList = memoList.values().stream()
            .map(MemoResponseDto::new).toList();
        // memoList.values(memoList 의 value 값을 가져옴)

        return responseList;

    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) throws IllegalAccessException {
        // 해당 메모가 DB에 존재하는지 확인
        if(memoList.containsKey(id)){
            // 해당 메모 가져오기
            Memo memo = memoList.get(id);
            // memo 수정
            memo.update(requestDto);
            return memo.getId();

        }else {
            throw new IllegalAccessException("선택한 메모는 존재하지 않습니다.");
        }
    }

    @DeleteMapping("memos/{id}")
    public Long deleteMemo(@PathVariable Long id) throws IllegalAccessException {
        // 해당 메모가 DB에 존재하는지 확인
        if(memoList.containsKey(id)){
            //해당 메모를 삭제
            memoList.remove(id);
        }else {
            throw new IllegalAccessException("선택한 메모는 존재하지 않습니다.");
        }
        return id;
    }
}
