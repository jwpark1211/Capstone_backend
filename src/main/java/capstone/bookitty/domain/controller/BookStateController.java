package capstone.bookitty.domain.controller;

import capstone.bookitty.domain.dto.ResponseType.BasicResponse;
import capstone.bookitty.domain.dto.ResponseType.ResponseCounter;
import capstone.bookitty.domain.dto.ResponseType.ResponseString;
import capstone.bookitty.domain.service.BookStateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static capstone.bookitty.domain.dto.BookStateDTO.*;

@Tag(name = "책 상태", description = "책 상태 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/state")
public class BookStateController {

    private final BookStateService bookStateService;

    @Operation(summary = "책 상태 생성")
    @PostMapping(path = "/new")
    public ResponseEntity<? extends BasicResponse> saveBookState(
            @RequestBody @Valid SaveRequest request
    ){
        return ResponseEntity.ok()
                .body(new ResponseCounter<IdResponse>(
                        bookStateService.saveState(request)));
    }

    @Operation(summary = "isbn으로 책 상태 리스트 가져오기")
    @GetMapping(path = "/isbn/{isbn}")
    public ResponseEntity<? extends BasicResponse> getStateByISBN(
            @PathVariable("isbn") String isbn
    ){
        return ResponseEntity.ok()
                .body(new ResponseCounter<List<InfoResponse>>(
                        bookStateService.findStateByISBN(isbn)));
    }

    @Operation(summary = "stateId로 책 상태 가져오기")
    @GetMapping(path = "/{state-id}")
    public ResponseEntity<? extends BasicResponse> getStateById(
            @PathVariable("state-id") Long stateId
    ){
        return ResponseEntity.ok()
                .body(new ResponseCounter<InfoResponse>(
                        bookStateService.findStateByStateId(stateId)));
    }

    @Operation(summary = "memberId로 책 상태 리스트 가져오기")
    @GetMapping(path = "/member/{member-id}")
    public ResponseEntity<? extends BasicResponse> getStateByMemberId(
            @PathVariable("member-id") Long memberId
    ){
        return ResponseEntity.ok()
                .body(new ResponseCounter<List<InfoResponse>>(
                        bookStateService.findStateByMemberId(memberId)));
    }

    @Operation(summary = "state 정보 수정")
    @PatchMapping(path = "/{state-id}")
    public ResponseEntity<? extends BasicResponse> updateState(
            @PathVariable("state-id") Long stateId,
            @RequestBody @Valid UpdateRequest request
    ){
        bookStateService.updateState(stateId, request);
        return ResponseEntity.ok()
                .body(new ResponseString("update state!"));
    }

    @Operation(summary = "state 삭제")
    @DeleteMapping(path = "/{state-id}")
    public ResponseEntity<? extends BasicResponse> deleteState(
            @PathVariable("state-id") Long stateId
    ){
        bookStateService.deleteState(stateId);
        return ResponseEntity.ok()
                .body(new ResponseString("delete state!"));
    }

}
