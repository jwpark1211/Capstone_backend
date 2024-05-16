package capstone.bookitty.domain.controller;

import capstone.bookitty.domain.dto.ResponseType.BasicResponse;
import capstone.bookitty.domain.dto.ResponseType.ResponseCounter;
import capstone.bookitty.domain.dto.ResponseType.ResponseString;
import capstone.bookitty.domain.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static capstone.bookitty.domain.dto.MemberDTO.*;

@Tag(name = "회원", description = "회원 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "deloy test")
    @GetMapping("/test")
    public String test(){
        return "DEPLOY 0515";
    }

    @Operation(summary = "회원가입")
    @PostMapping(path = "/new")
    public ResponseEntity<? extends BasicResponse> save(
            @RequestBody @Valid SaveRequest request){
        return ResponseEntity.ok()
                .body(new ResponseCounter<IdResponse>(
                       memberService.saveMember(request)));
    }

    @Operation(summary = "이메일 중복 확인")
    @GetMapping(path = "/email/{email}/unique")
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

    @Operation(summary = "전체 회원 조회 / page는 requestParam으로 요청할 수 있습니다. / "+
    "size(한 페이지 당 element 수, default = 10), page(요청하는 페이지, 0부터 시작)")
    @GetMapping(path = "/all")
    public ResponseEntity<? extends BasicResponse> findAllMembers(
            @PageableDefault(sort="id",size = 10) Pageable pageable
    ){
        return ResponseEntity.ok(
                new ResponseCounter<Page<InfoResponse>>(
                        memberService.getAllMemberInfo(pageable)));
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
