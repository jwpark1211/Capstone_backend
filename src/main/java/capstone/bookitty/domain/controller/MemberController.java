package capstone.bookitty.domain.controller;

import capstone.bookitty.domain.dto.ResponseType.BasicResponse;
import capstone.bookitty.domain.dto.ResponseType.ResponseCounter;
import capstone.bookitty.domain.dto.ResponseType.ResponseString;
import capstone.bookitty.domain.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static capstone.bookitty.domain.dto.MemberDTO.*;

@Tag(name = "회원", description = "회원 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입")
    @PostMapping(path = "/new")
    public ResponseEntity<? extends BasicResponse> save(
            @RequestBody @Valid SaveRequest request){
        return ResponseEntity.ok()
                .body(new ResponseCounter<IdResponse>(
                       memberService.saveMember(request)));
    }

    @Operation(summary = "이메일 중복 확인")
    @PostMapping(path = "/email/{email}/unique")
    public ResponseEntity<? extends BasicResponse> isEmailUnique(
            @PathVariable("email") String email
    ){
        return ResponseEntity.ok()
                .body(new ResponseCounter<BoolResponse>(
                        memberService.isEmailUnique(email)));
    }

    @Operation(summary = "로그인")
    @PostMapping(path = "/login")
    public ResponseEntity<? extends BasicResponse> login(
            @RequestBody @Valid LoginRequest request
    ){
        memberService.login(request);
        return ResponseEntity.ok()
                .body(new ResponseString("login success!"));
    }

    @Operation(summary = "id로 회원 조회")
    @GetMapping(path = "/{id}")
    public ResponseEntity<? extends BasicResponse> findOneMember(
            @PathVariable("id") Long memberId
    ){
        return ResponseEntity.ok(
                new ResponseCounter<InfoResponse>(
                        memberService.getMemberInfoWithId(memberId)));
    }

    @Operation(summary = "전체 회원 조회")
    @GetMapping(path = "/all")
    public ResponseEntity<? extends BasicResponse> findAllMembers(){
        return ResponseEntity.ok(
                new ResponseCounter<List<InfoResponse>>(
                        memberService.getAllMemberInfo()));
    }

    /* TODO : S3
    @PostMapping(path = "/{id}/profile")
    public ResponseEntity<? extends BasicResponse> updateMemberProfile(
            @PathVariable("id") Long memberId,
            @RequestPart(value = "profile") MultipartFile profileImg
            ) throws IOException {
        memberService.updateProfile(memberId, profileImg);
        return ResponseEntity.ok().build();
    }*/

    @Operation(summary = "회원 탈퇴/추가 구현 필요(미완성)")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<? extends BasicResponse> deleteMember(
            @PathVariable("id") Long memberId
    ){
        memberService.deleteMember(memberId);
        return ResponseEntity.ok()
                .body(new ResponseString("delete member!"));
    }

}
